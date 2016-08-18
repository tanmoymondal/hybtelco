package org.bonstore.storefront.security;

import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.security.StorefrontAuthenticationSuccessHandler;
import de.hybris.platform.commerceservices.order.CommerceCartMergingException;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.core.Constants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bonstore.storefront.facades.BonStoreCartFacade;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
/**
 * Created by Tanmoy_Mondal on 8/10/2016.
 */
public class BonStoreAuthenticationSuccessHandler extends StorefrontAuthenticationSuccessHandler
{

    private static final String CART_MERGED = "cartMerged";
    private static final Logger LOG = Logger.getLogger(BonStoreAuthenticationSuccessHandler.class);
    private BonStoreCartFacade bonStoreCartFacade;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException, ServletException
    {
        //if redirected from some specific url, need to remove the cachedRequest to force use defaultTargetUrl
        final RequestCache requestCache = new HttpSessionRequestCache();
        final SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null)
        {
            for (final String redirectUrlForceDefaultTarget : getListRedirectUrlsForceDefaultTarget())
            {
                if (savedRequest.getRedirectUrl().contains(redirectUrlForceDefaultTarget))
                {
                    requestCache.removeRequest(request, response);
                    break;
                }
            }
        }

        getCustomerFacade().loginSuccess();
        request.setAttribute(CART_MERGED, Boolean.FALSE);

        // Check if the user is in role admingroup
        if (!isAdminAuthority(authentication))
        {
            if (!getCartFacade().hasEntries())
            {
                restoreSavedCart();
            }
            else
            {
                restoreSavedCartAndMerge(request);
            }

            getBruteForceAttackCounter().resetUserCounter(getCustomerFacade().getCurrentCustomerUid());
            super.onAuthenticationSuccess(request, response, authentication);
        }
        else
        {
            LOG.warn("Invalidating session for user in the " + Constants.USER.ADMIN_USERGROUP + " group");
            invalidateSession(request, response);
        }
    }

    @Override

    protected void restoreSavedCartAndMerge(final HttpServletRequest request)
    {
        final String sessionCartGuid = getCartFacade().getSessionCartGuid();
        final String mostRecentSavedCartGuid = getMostRecentSavedCartGuid(sessionCartGuid);
        if (StringUtils.isNotEmpty(mostRecentSavedCartGuid))
        {
            getSessionService().setAttribute(WebConstants.CART_RESTORATION_SHOW_MESSAGE, Boolean.TRUE);
            try
            {
                getSessionService().setAttribute(WebConstants.CART_RESTORATION,
                        getBonStoreCartFacade().restoreCartAndMerge(mostRecentSavedCartGuid, sessionCartGuid));
                request.setAttribute(CART_MERGED, Boolean.TRUE);
            }
            catch (final CommerceCartRestorationException e)
            {
                if (LOG.isDebugEnabled())
                {
                    LOG.debug(e);
                }
                getSessionService().setAttribute(WebConstants.CART_RESTORATION_ERROR_STATUS,
                        WebConstants.CART_RESTORATION_ERROR_STATUS);
            }
            catch (final CommerceCartMergingException e)
            {
                LOG.error("User saved cart could not be merged", e);
            }
        }
    }

    public BonStoreCartFacade getBonStoreCartFacade()
    {
        return bonStoreCartFacade;
    }

    @Required
    public void setBonStoreCartFacade(final BonStoreCartFacade bonStoreCartFacade)
    {
        this.bonStoreCartFacade = bonStoreCartFacade;
    }


}

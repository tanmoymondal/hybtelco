package org.bonstore.storefront.facades;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.data.CartRestorationData;
import de.hybris.platform.commercefacades.order.impl.DefaultCartFacade;
import de.hybris.platform.commerceservices.order.CommerceCartMergingException;
import de.hybris.platform.commerceservices.order.CommerceCartRestoration;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;

import org.bonstore.storefront.order.impl.BonStoreCommerceCartMergingStrategy;
import org.springframework.beans.factory.annotation.Required;


/**
 * Created by Tanmoy_Mondal on 8/10/2016.
 */
public class BonStoreCartFacade extends DefaultCartFacade
{

    private BonStoreCommerceCartMergingStrategy bonStoreCommerceCartMergingStrategy;

    @Override
    public CartRestorationData restoreCartAndMerge(final String fromUserCartGuid, final String toUserCartGuid)
            throws CommerceCartRestorationException, CommerceCartMergingException
    {
        final BaseSiteModel currentBaseSite = getBaseSiteService().getCurrentBaseSite();
        final CartModel fromCart = getCommerceCartService().getCartForGuidAndSiteAndUser(fromUserCartGuid, currentBaseSite,
                getUserService().getCurrentUser());

        final CartModel toCart = getCommerceCartService().getCartForGuidAndSiteAndUser(toUserCartGuid, currentBaseSite,
                getUserService().getCurrentUser());

        if (fromCart == null && toCart != null)
        {
            return restoreSavedCart(toUserCartGuid);
        }

        if (fromCart != null && toCart == null)
        {
            return restoreSavedCart(fromUserCartGuid);
        }

        if (fromCart == null && toCart == null)
        {
            return null;
        }

        final CommerceCartParameter parameter = new CommerceCartParameter();
        parameter.setEnableHooks(true);
        parameter.setCart(toCart);

        final CommerceCartRestoration restoration = getCommerceCartService().restoreCart(parameter);
        parameter.setCart(getCartService().getSessionCart());

        getBonStoreCommerceCartMergingStrategy().mergeCarts(fromCart, parameter.getCart(), restoration.getModifications());

        final CommerceCartRestoration commerceCartRestoration = getCommerceCartService().restoreCart(parameter);

        commerceCartRestoration.setModifications(restoration.getModifications());

        getCartService().changeCurrentCartUser(getUserService().getCurrentUser());
        return getCartRestorationConverter().convert(commerceCartRestoration);
    }

    public BonStoreCommerceCartMergingStrategy getBonStoreCommerceCartMergingStrategy()
    {
        return bonStoreCommerceCartMergingStrategy;
    }

    @Required
    public void setBonStoreCommerceCartMergingStrategy(
            final BonStoreCommerceCartMergingStrategy bonStoreCommerceCartMergingStrategy)
    {
        this.bonStoreCommerceCartMergingStrategy = bonStoreCommerceCartMergingStrategy;
    }

}

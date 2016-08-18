package org.bonstore.storefront.order.impl;

import de.hybris.platform.commerceservices.order.CommerceCartMergingException;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartMergingStrategy;
import de.hybris.platform.core.model.order.CartModel;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * Created by Tanmoy_Mondal on 8/10/2016.
 */
public class BonStoreCommerceCartMergingStrategy extends DefaultCommerceCartMergingStrategy
{

    private final static Logger LOG = Logger.getLogger(BonStoreCommerceCartMergingStrategy.class);

    @Override
    public void mergeCarts(final CartModel fromCart, final CartModel toCart, final List<CommerceCartModification> modifications)
            throws CommerceCartMergingException
    {
        super.mergeCarts(fromCart, toCart, modifications);
    }
}

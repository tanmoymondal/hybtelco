package org.bonstore.storefront.forms;

/**
 * Created by Tanmoy_Mondal on 8/10/2016.
 */
public class OrganizationForm {

    private String id;
    private String name;
    private String phonenumber;
    private String email;

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id
     *           the id to set
     */
    public void setId(final String id)
    {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *           the name to set
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * @return the phonenumber
     */
    public String getPhonenumber()
    {
        return phonenumber;
    }

    /**
     * @param phonenumber
     *           the phonenumber to set
     */
    public void setPhonenumber(final String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email
     *           the email to set
     */
    public void setEmail(final String email)
    {
        this.email = email;
    }
}

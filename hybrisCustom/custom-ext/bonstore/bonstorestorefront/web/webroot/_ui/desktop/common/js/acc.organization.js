ACC.organization = {

	spinner: $("<img src='" + ACC.config.commonResourcePath + "/images/spinner.gif' />"),
	addressID: '',

	showOrganizations: function ()
	{
		$(document).on("click", "#viewOrganizations", function ()
		{
			var data = $("#savedOrganizationListHolder").html();
			$.colorbox({

				height: false,
				html: data,
				onComplete: function ()
				{

					$(this).colorbox.resize();
				}
			});

		})
	},

	bindToColorboxClose: function ()
	{
		$(document).on("click", ".closeColorBox", function ()
		{
			$.colorbox.close();
		})
	},

	showRemoveOrganizationConfirmation: function ()
	{
		$(document).on("click", ".removeOrganizationButton", function ()
		{
			var organizationId = $(this).data("id");
			$.colorbox({
				inline: true,
				height: false,
				href: "#popup_confirm_organization_removal_" + organizationId,
				onComplete: function ()
				{

					$(this).colorbox.resize();
				}
			});

		})
	}
}

// Address Verification
$(document).ready(function ()
{
	with (ACC.organization)
	{

		showOrganizations();
		showRemoveOrganizationConfirmation();
		bindToColorboxClose();
	}
});
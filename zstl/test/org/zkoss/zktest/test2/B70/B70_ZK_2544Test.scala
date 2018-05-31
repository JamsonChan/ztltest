package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags

@Tags(tags = "B70-ZK-2544.zul")
class B70_ZK_2544Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2544.zul

	Purpose:
		
	Description:
		
	History:
		Thu, Feb 26, 2015 10:13:08 AM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk xmlns:w="client">
	Please click the combobox's button, the listitem should be selected, and you should not see any "clicked" in zk client log
    <zscript><![CDATA[
    ListModelList model = new ListModelList();
    model.add("1");
    model.add("2");
    model.add("3");
    ]]></zscript>
    <listbox model="${model}" nonselectableTags="" onSelect="System.out.println(self.selectedItem)" width="100%">
        <listhead>
            <listheader label="Combobox" width="100px" />
            <listheader label="Textbox"  />
        </listhead>
        <template name="model" var="record">
            <listitem value="${record}" onClick='Clients.log("Clicked")'>
                <listcell>
                    <combobox model="${model}" width="100%">
                        <template name="model" var="option">
                            <comboitem label="${option}" />
                        </template>
                    </combobox>
                </listcell>
                <listcell>
                    <textbox />
                    <button label="test" />
                </listcell>
            </listitem>
        </template>
    </listbox>
</zk>
    
"""
    runZTL(zscript,
      () => {
        click(jq(".z-combobox-button").eq(0));
        waitResponse();
        verifyEquals("Clicked", jq("#zk_log").eval("val()").trim());
        verifyTrue(jq(".z-listitem").eq(0).hasClass("z-listitem-selected"));
      })

  }
}
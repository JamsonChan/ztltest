package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "B70-ZK-2551.zul")
class B70_ZK_2551Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2551.zul

	Purpose:
		
	Description:
		
	History:
		Tue, Jan 27, 2015  6:16:45 PM, Created by hanhsu

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
  <window border="normal" title="hello" >
    	<vbox>
          <label value="1. Scroll to the far right in the listbox"/>
          <label value="2. Click the button to fire CONTENTS_CHANGED for first item in model"/>
          <label value="3. The horisontal scroll will be reset to its leftmost position"/>
          <label value="4. Remove the paging mold from the listbox and the bug will not appear"/>
    ROD false
  	<listbox id="lb" mold="paging" width="300px" height="200px" >	
  	<custom-attributes org.zkoss.zul.listbox.rod="false"/>
          <listhead>
            <listheader id="listheader2" label="col2" width="350px"/>            
            <listheader id="listheader1" label="col1" hflex="min"/>
          </listhead>
          <template name="model">
          	<listitem>
          		<listcell>${each}</listcell>
          		<listcell>${each}</listcell>
          	</listitem>
          </template>
    	</listbox>
  	
	<button id="btn" label="Click to fire CONTENTS_CHANGED for first item in model." onClick="changeModel()"/>
  <zscript><![CDATA[
	ListModelList model = new ListModelList();
	model.add("a");
	model.add("b");

	lb.setModel(model);
	public void changeModel() {
		model.set(1, "replaced");
//		model.add("added");
	}
]]></zscript>
  </vbox>
  </window>
</zk>
    
"""
    runZTL(zscript,
      () => {
        var listbox = jq("@listbox");

        horScroll(listbox, 100);
        waitResponse();
        click(jq("@button"));
        waitResponse();
        verifyTrue(getScrollLeft(listbox.toWidget()) != 0);
      })

  }
}
package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "B70-ZK-2550.zul")
class B70_ZK_2550Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2550.zul

	Purpose:
		
	Description:
		
	History:
		Tue, Jan 27, 2015 12:34:18 PM, Created by hanhsu

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
  <window border="normal" title="hello" >
    	<vbox>
          <label value="1. Scroll to the far right in the listbox"/>
          <label value="2. Click the button to fire CONTENTS_CHANGED for first item in model"/>
          <label value="3. The horisontal scroll should not be reset to its leftmost position"/>
    Listbox
  	<listbox id="lb" mold="paging" width="300px" height="120px" >
        <listhead>
        	<listheader label="col2" width="400px"/>            
    	</listhead>
    </listbox>
    Grid
    <grid id="gd" mold="paging" width="300px" height="120px" >
        <columns >
        	<column label="col2" width="400px"/>            
    	</columns >
    </grid>
	<button id="btn" label="Click to fire CONTENTS_CHANGED for first item in model." onClick="changeModel()"/>
  <zscript><![CDATA[
	ListModelList model = new ListModelList();
	model.add("a");
	model.add("b");
	model.add("a");
	model.add("b");
	model.add("a");
	model.add("b");
	model.add("a");
	model.add("b");
	model.add("a");
	model.add("b");
                                                                 
	lb.setModel(model);
	gd.setModel(model);
	public void changeModel() {
		model.set(0, "replaced");
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
        var grid = jq("@grid");

        horScroll(listbox, 100);
        waitResponse();
        var list_before = getScrollTop(listbox.toWidget());
        horScroll(grid, 100);
        waitResponse();
        var grid_before = getScrollTop(grid.toWidget());
        click(jq("@button"));
        waitResponse();
        verifyTrue(getScrollTop(listbox.toWidget()) == list_before);
        verifyTrue(getScrollTop(grid.toWidget()) == grid_before);
      })

  }
}
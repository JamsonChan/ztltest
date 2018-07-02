package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "B70-ZK-2279.zul")
class B70_ZK_2279Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2279.zul

	Purpose:
		
	Description:
		
	History:
		Thu, May 08, 2014 12:29:06 PM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
<zscript>
import org.zkoss.bind.Converter;
import java.sql.Timestamp;
/**
*
* @author Sitansu
*/
public class IndexVm {
  
  private Timestamp theNumber;
  private Timestamp theNumber2;
  
  public IndexVm () {}
  
  public Timestamp getTheNumber () {
      return theNumber;
  }
  
  public Timestamp getTheNumber2 () {
      return theNumber2;
  }
  
}
</zscript>
  <window border="normal" closable="false"
            apply="org.zkoss.bind.BindComposer"
            viewModel="@id('vm') @init('IndexVm')" width="100%" height="100%">
                <vbox>
                <label multiline="true">
                1. Please click the first datebox, and the popup calendar will be shown.
                2. Click the right arrow of the calendar slowly, you should not be able to see the second datebox's icon.
                </label>
              <datebox value="@load(vm.theNumber)" readonly="true" />
              
              <datebox value="@load(vm.theNumber2)" />
              </vbox>
              <hbox>
                  <datebox value="@load(vm.theNumber)" readonly="true" />
              
              <datebox value="@load(vm.theNumber2)" />
              </hbox>
  </window>
</zk>
"""
    runZTL(zscript,
      () => {
        click(jq("@datebox").eq(0).find(".z-datebox-button"));
        waitResponse();
        click(jq(".z-datebox-open .z-calendar-right"));
        waitResponse();
        verifyImage();
      })

  }
}
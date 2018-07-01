package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "B70-ZK-2414.zul")
class B70_ZK_2414Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2414.zul

	Purpose:
		
	Description:
		
	History:
		Fri, Aug 22, 2014  2:30:33 PM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
	<window id="mainWin" width="100%" height="100%" sclass="page-bg" >
		<div width="100%" vflex="80">
			<hbox hflex="1" vflex="1">
				<!-- empty div for alignment -->
				<div hflex="15">You should be able to see the "right" word in IE9/IE10</div>
				<div id="contentDiv" hflex="70" vflex="1">
				    <div id="financialDetailsWin" vflex="1" >
				        <panel id="contentPanel" height="100%" hflex="1">
				            <panelchildren style="overflow:auto;border:solid 1px black">
				            	center
				            </panelchildren>
				        </panel>
				        <!-- SPACE element here causes the problem;
				           -    - caused ZK 7 width-calculation on IE to explode the width to 1600+ pixels.
				        -->
				        <space/>
				    </div>				 
				</div>
				<div hflex="15" id="rLabel">right</div>
			</hbox>
		</div>
	</window>
</zk>

"""
    runZTL(zscript,
      () => {
        verifyTrue(jq("$rLabel").exists());
      })

  }
}
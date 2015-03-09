package org.zkoss.zktest.test2.B70

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test
import java.awt.event.KeyEvent
import org.openqa.selenium.Keys
import org.zkoss.ztl.ZKSeleneseTestBase

@Tags(tags = "B70-ZK-2587.zul")
class B70_ZK_2587Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2587.zul

	Purpose:
		
	Description:
		
	History:
		Tue, Feb 24, 2015  3:46:31 PM, Created by Chunfu

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk xmlns:x="xhtml">
	<label multiline="true">
	1. in firefox, drag red label to tab "drop here"
	2. you should see tab label changed to "dropped: 'in tabbox'"
	</label>
	<div style="background:grey;" draggable="true" width="200px" height="30px">
		<label value="not in tabbox" style="background-color: green;"/>
	</div>

	<tabbox width="200px" height="200px">
		<tabs>
			<tab droppable="true" onDrop="move2(event.dragged)" label="drop here" />
		</tabs>
		<tabpanels>
			<tabpanel>
				<div style="background:grey;" draggable="true" width="200px" height="30px">
					<label value="in tabbox" style="background-color: red;"/>
					<!-- <label value="in tabbox (work around)" style="background-color: yellow; pointer-events: none;"/> -->
				</div>
			</tabpanel>
		</tabpanels>
	</tabbox>
	<zscript>
		void move2(Component dragged) {
			self.setLabel("dropped: '" + dragged.getFirstChild().getValue() + "'");
		}
	</zscript>
</zk>
    
"""  
  runZTL(zscript,
    () => {
      var label = jq(".z-tabpanel .z-label");
      var tab = jq(".z-tab");
      dragdropToObject(label, tab, "0,0", "0,0");
      waitResponse();
      
      verifyEquals("dropped: 'in tabbox'", jq(".z-tab .z-tab-text").text());
    })
    
  }
}
package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags

@Tags(tags = "B70-ZK-2310.zul")
class B70_ZK_2310Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2310.zul

	Purpose:
		
	Description:
		
	History:
		Wed, May 28, 2014  3:47:24 PM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
Please maximize anyone of the panels, and then click the "add Panel" button, it should not cause any JS error. 
<button id="addPanel" label="add Panel">
<attribute name="onClick">
Panel p = new Panel();
p.setParent(pc);
</attribute>
</button>
<portallayout maximizedMode="whole">
	<portalchildren id="pc" style="padding: 5px" width="40%">
		<panel id="p1" height="200px" title="Google Tools" border="normal" collapsible="true" closable="true" maximizable="true" style="margin-bottom:10px">
			<panelchildren>
				<iframe width="100%" height="100%" src="http://3.gmodules.com/ig/ifr?url=http://www.google.com/ig/modules/toolspromo.xml&amp;nocache=0&amp;lang=en&amp;country=us&amp;.lang=en&amp;.country=us&amp;synd=ig&amp;mid=3&amp;ifpctok=5090480830848781425&amp;parent=http://www.google.com&amp;extern_js=/extern_js/f/CgJlbhICdXMrMAs4ACwrMBA4ACwrMBI4ACwrMBM4ACw/zYieI_ujwr4.js"/>
			</panelchildren>
		</panel>
		<panel id="p2" height="300px" title="LabPixies Clock" border="normal" collapsible="true" closable="true" maximizable="true" style="margin-bottom:10px">
			<panelchildren>
				<iframe width="100%" height="100%" src="http://ig.gmodules.com/gadgets/ifr?url=http://www.labpixies.com/campaigns/clock/mini_clock.xml&amp;nocache=0&amp;up_skin_id=&amp;upt_skin_id=hidden&amp;lang=en&amp;country=us&amp;.lang=en&amp;.country=us&amp;synd=ig&amp;mid=34&amp;ifpctok=-3234052241260630457&amp;parent=http://www.google.com&amp;extern_js=/extern_js/f/CgJlbhICdXMrMBI4ACwrMBM4ACw/v3vgcgA0x8g.js"/>
			</panelchildren>
		</panel>
	</portalchildren>
	
	<portalchildren style="padding: 5px" width="50%">
		<panel id="p3" height="400px" title="Trio" border="normal" collapsible="true" closable="true" maximizable="true" style="margin-bottom:10px">
			<panelchildren>
				<iframe width="100%" height="100%" src="http://ig.gmodules.com/gadgets/ifr?url=http://www.labpixies.com/campaigns/trio/trio.xml&amp;nocache=0&amp;lang=en&amp;country=us&amp;.lang=en&amp;.country=us&amp;synd=ig&amp;mid=35&amp;ifpctok=7430207052966295609&amp;parent=http://www.google.com&amp;extern_js=/extern_js/f/CgJlbhICdXMrMBI4ACwrMBM4ACw/v3vgcgA0x8g.js"/>
			</panelchildren>
		</panel>
	</portalchildren>
</portallayout>
</zk>
"""
    runZTL(zscript,
      () => {
        val btn = jq("$addPanel");

        val p1 = jq("$p1");
        val p2 = jq("$p2");
        val p3 = jq("$p3");

        val p1Full = p1.find(".z-panel-maximize");
        val p2Full = p2.find(".z-panel-maximize");
        val p3Full = p3.find(".z-panel-maximize");

        click(p1Full);
        waitResponse();
        click(btn);
        waitResponse();
        verifyFalse(jq(".z-error").exists());
        val p1Small = p1.find(".z-panel-maximized");
        click(p1Small);
        waitResponse();

        click(p2Full);
        waitResponse();
        click(btn);
        waitResponse();
        verifyFalse(jq(".z-error").exists());
        val p2Small = p2.find(".z-panel-maximized");
        click(p2Small);
        waitResponse();

        click(p3Full);
        waitResponse();
        click(btn);
        waitResponse();
        verifyFalse(jq(".z-error").exists());
      })

  }
}
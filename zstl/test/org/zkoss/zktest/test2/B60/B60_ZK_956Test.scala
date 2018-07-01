/* B60_ZK_956Test.scala

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Mon Apr 23 11:42:00 CST 2012 , Created by benbai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zktest.test2.B60

import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.unit.Widget
import org.zkoss.ztl._
import org.zkoss.ztl.unit._
import org.zkoss.ztl.annotation.Tags

/**
  * A test class for bug ZK-956
  *
  * @author benbai
  *
  */
@Tags(tags = "B60-ZK-956.zul,B,M,Tooltip")
class B60_ZK_956Test extends ZTL4ScalaTestCase {

  def testClick() = {
    val zscript =
      """
			<zk>
				<h:pre xmlns:h="html">
					Click the button and it shouldn't show up Exception message.
				</h:pre>
				<zscript>
					public void addLabelAndTooltip (Component comp) {
						Div container = new Div(); // dettached, imagine we DON'T have parent component at this point, e.g. macro component is being constructed
						Popup tooltip = new Popup();
						Label tooltiptext = new Label(" this is tool tip");
						tooltiptext.setParent(tooltip);
						Label label = new Label();
						label.setValue(" new label");
						label.setTooltip(tooltip); // tooltip is still dettached; anonymous UUID is used as tooltip ref
						label.setParent(container);
						tooltip.setParent(container);
						container.setParent(comp); // everything is attached; tooltip gets normal UUID; label still uses anonymous UUID as tooltip ref
						
						Popup tooltip2 = new Popup();
						Label tooltiptext2 = new Label(" this is tool tip 2");
						tooltiptext2.setParent(tooltip2);
						tooltip2.setParent(comp);
						Label l = new Label();
						l.setValue(" new label 2");
						l.setTooltip(tooltip2);
						l.setParent(comp);
					}
				</zscript>
				<window id="win" title="test win" border="normal" width="200px" height="200px"  />
					 
			    <button id="btn" label="tset" onClick="addLabelAndTooltip(win);" />
			</zk>

    """

    runZTL(zscript,
      () => {
        var btn: Widget = engine.$f("btn");

        click(btn);
        waitResponse();
        verifyFalse("No exception",
          jq(".z-window-modal").exists());
      }
    );
  }
}
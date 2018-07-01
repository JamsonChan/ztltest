package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "B70-ZK-2559.zul")
class B70_ZK_2559Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2559.zul

	Purpose:
		
	Description:
		
	History:
		Wed, Jan 28, 2015 12:51:01 PM, Created by Chunfu

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
	<zscript><![CDATA[
		public void addColumn(Columns parent, String label, String width) {
			Column col = new Column(label);
			col.setWidth(width);
			parent.appendChild(col);
		}
		public void addAuxheader(Auxhead parent, String label, int colSpan) {
			Auxheader auxheader = new Auxheader(label);
			auxheader.setColspan(colSpan);
			parent.appendChild(auxheader);
		}
	]]></zscript>
	<label multiline="true">
	1. click grid tab
	2. you should not see js error
	</label>
	<tabbox id="tb">
		<tabs>
			<tab label="start"/>
			<tab label="grid">
				<attribute name="onClick"><![CDATA[
				    Auxhead auxhead1 = new Auxhead();
				    gr.appendChild(auxhead1);
				    addAuxheader(auxhead1, "", 1);
				    addAuxheader(auxhead1, "AH 1.1", 1);
				    addAuxheader(auxhead1, "AH 1.2", 1);
				    
				    addColumn(cols, "AAA", "160px");
				    addColumn(cols, "BBB", "80px");
				    addColumn(cols, "CCC", "80px");
				]]></attribute>
			</tab>
			<tab label="other"/>
		</tabs>
		<tabpanels>
			<tabpanel>start content</tabpanel>
			<tabpanel>
				<grid id="gr" hflex="min">
					<columns id="cols" sizable="true">
					</columns>
					<rows>
						<row><label value="aaa value"/><label value="bbb value"/><label value="ccc value"/></row>
						<row><label value="aaa value"/><label value="bbb value"/><label value="ccc value"/></row>
						<row><label value="aaa value"/><label value="bbb value"/><label value="ccc value"/></row>
					</rows>
				</grid>
			</tabpanel>
			<tabpanel>other content</tabpanel>
		</tabpanels>
	</tabbox>
</zk>
    
"""
    runZTL(zscript,
      () => {
        var tab = jq(".z-tab").eq(1);
        click(tab);
        waitResponse();
        var errbox = jq(".z-error");
        verifyTrue(!errbox.isVisible());
      })

  }
}
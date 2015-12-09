package org.zkoss.zktest.test2.B70

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test
import java.awt.event.KeyEvent
import org.openqa.selenium.Keys
import org.zkoss.ztl.ZKSeleneseTestBase

@Tags(tags = "B70-ZK-2640.zul")
class B70_ZK_2640Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2640.zul

	Purpose:
		
	Description:
		
	History:
		Mon, Mar 02, 2015  3:49:59 PM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
	<label multiline="true">
	1. Focus on the first textbox
	2. Press "tab" key until header scrolls to the left
	3. The content of the body should scroll to where its header is.
	</label>
    <custom-attributes org.zkoss.zul.nativebar="false" />
    <window title="Listbox (ZK ${desktop.webApp.version})" border="normal" hflex="min">
        <listbox width="300px">
            <listhead sizable="true">
                <listheader
                    align="center"
                    width="40px"
                    forEach="1,2,3">
                    <textbox width="20px" />
                </listheader>
                <listheader
                    width="90px"
                    label="filter">
                    <textbox width="70px" />
                </listheader>
                <listheader
                    width="90px"
                    label="filter ${forEachStatus.index + 1}"
                    forEach="1,2,3,4,5,6,7,8">
                    <textbox width="70px" />
                </listheader>
            </listhead>
            <listitem>
                <listcell />
                <listcell />
                <listcell />
                <listcell label="ZK Jet 0.8.0 is released" />
                <listcell
                    label="2008/11/${forEachStatus.index + 1}"
                    forEach="1,2,3,4,5,6,7,8" />
            </listitem>
        </listbox>
    </window>
    <window title="Grid (ZK ${desktop.webApp.version})" border="normal" hflex="min">
        <grid width="300px">
            <columns sizable="true">
                <column
                    align="center"
                    width="40px"
                    forEach="1,2,3">
                    <textbox width="20px" />
                </column>
                <column
                    width="90px"
                    label="filter">
                    <textbox width="70px" />
                </column>
                <column
                    width="90px"
                    label="filter ${forEachStatus.index + 1}"
                    forEach="1,2,3,4,5,6,7,8">
                    <textbox width="70px" />
                </column>
            </columns>
            <rows>
                <row>
                    <cell />
                    <cell />
                    <cell />
                    <cell>ZK Jet 0.8.0 is released</cell>
                    <cell forEach="1,2,3,4,5,6,7,8">
                        2008/11/${forEachStatus.index + 1}
                    </cell>

                </row>
            </rows>
        </grid>
    </window>
    <window title="Tree (ZK ${desktop.webApp.version})" border="normal" hflex="min">
        <tree width="300px">
            <treecols sizable="true">
                <treecol
                    align="center"
                    width="40px"
                    forEach="1,2,3">
                    <textbox width="20px" />
                </treecol>
                <treecol
                    width="90px"
                    label="filter">
                    <textbox width="70px" />
                </treecol>
                <treecol
                    width="90px"
                    label="filter ${forEachStatus.index + 1}"
                    forEach="1,2,3,4,5,6,7,8">
                    <textbox width="70px" />
                </treecol>
            </treecols>
            <treechildren>
                <treeitem>
                    <treerow>
                        <treecell />
                        <treecell />
                        <treecell />
                        <treecell>ZK Jet 0.8.0 is released</treecell>
                        <treecell forEach="1,2,3,4,5,6,7,8">
                            2008/11/${forEachStatus.index + 1}
                        </treecell>
                    </treerow>
                </treeitem>
            </treechildren>
        </tree>
    </window>
</zk>
    
"""  
  runZTL(zscript,
    () => {
      var textbox = jq(".z-listbox .z-textbox")
      click(textbox.first);
      waitResponse();
      for (i <- 0 to 10) {
        sendKeys(textbox.eq(i), Keys.TAB);
      }
      verifyTrue(jq(".z-listbox .z-scrollbar").css("left") != 0);
      
      textbox = jq(".z-grid .z-textbox");
      click(textbox.eq(0));
      waitResponse();
      for (i <- 0 to 10) {
        sendKeys(textbox.eq(i), Keys.TAB);
      }
      verifyTrue(jq(".z-grid .z-scrollbar").css("left") != 0);
      
      textbox = jq(".z-tree .z-textbox");
      click(textbox.eq(0));
      waitResponse();
      for (i <- 0 to 10) {
        sendKeys(textbox.eq(i), Keys.TAB);
      }
      verifyTrue(jq(".z-tree .z-scrollbar").css("left") != 0);
    })
    
  }
}
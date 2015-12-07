package org.zkoss.zktest.test2.B70

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test
import java.awt.event.KeyEvent
import org.openqa.selenium.Keys
import org.zkoss.ztl.ZKSeleneseTestBase

@Tags(tags = "B70-ZK-2395.zul")
class B70_ZK_2395Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2395.zul

	Purpose:
		
	Description:
		
	History:
		Mon, Aug 11, 2014  15:49:12 AM, Created by jerrychen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
	<zscript>
	Object[] obj = new Object[50];
	</zscript>
	
	<div>
		if you see the header and content misaligned, it's a bug.
	</div>
	<hlayout>
		<div>
			<custom-attributes org.zkoss.zul.nativebar="true"/>
			<button label="show1" onClick="c1.setVisible(true);cc1.setVisible(true);ccc1.setVisible(true)" />
			<button label="show3" onClick="c3.setVisible(true);cc3.setVisible(true);ccc3.setVisible(true)" />
			<div height="300px" width="700px">
				<tree id="myTreeA" vflex="1" hflex="1">
					<treecols sizable="true">
						<treecol label="c0" id="c0" hflex="2" />
						<treecol label="c1" id="c1" hflex="1" visible="false" />
						<treecol label="c2" id="c2" hflex="1" />
						<treecol label="c3" id="c3" hflex="1" visible="false" />
						<treecol label="c4" id="c4" hflex="1" />
					</treecols>
					<treechildren>
						<treeitem forEach="${obj}" open="false">
							<treerow>
								<treecell label="Row A ${forEachStatus.index}" />
								<treecell label="0.0" />
								<treecell label="0.0" />
								<treecell label="0.0" />
								<treecell label="0.0" />
							</treerow>
							<treechildren>
								<treeitem>
									<treerow>
										<treecell
											label="Row A ${forEachStatus.index} X" />
										<treecell label="0.0" />
										<treecell label="0.0" />
										<treecell label="0.0" />
										<treecell label="0.0" />
									</treerow>
								</treeitem>
								<treeitem>
									<treerow>
										<treecell
											label="Row A ${forEachStatus.index} X" />
										<treecell label="0.0" />
										<treecell label="0.0" />
										<treecell label="0.0" />
										<treecell label="0.0" />
									</treerow>
								</treeitem>
							</treechildren>
						</treeitem>
					</treechildren>
					<treefoot>
			            <treefooter label="ft0" />
			            <treefooter label="ft1" />
			            <treefooter label="ft2" />
			            <treefooter label="ft3" />
			            <treefooter label="ft4" />
			        </treefoot>
				</tree>
			</div>
			<div height="300px" width="700px">
				<listbox vflex="1" hflex="1">
			        <listhead sizable="true">
			            <listheader label="h0" hflex="2" />
			            <listheader label="h1" id="cc1" hflex="1" visible="false" />
			            <listheader label="h2" hflex="1" />
			            <listheader label="h3" id="cc3" hflex="1" visible="false" />
			            <listheader label="h4" hflex="1" />
			        </listhead>
			        <listitem forEach="${obj}">
			            <listcell label="0" />
			            <listcell label="0" />
			            <listcell label="0" />
			            <listcell label="0" />
			            <listcell label="0" />
			        </listitem>
			        <listfoot>
			            <listfooter label="f0"/>
			            <listfooter label="f1"/>
			            <listfooter label="f2"/>
			            <listfooter label="f3"/>
			            <listfooter label="f4"/>
			        </listfoot>
			    </listbox>
			</div>
			<div height="300px" width="700px">
				<grid vflex="1" hflex="1">
				    <columns sizable="true">
				        <column label="c0" hflex="2"/>
				        <column label="c1" id="ccc1" hflex="1" visible="false"/>
				        <column label="c2" hflex="1"/>
				        <column label="c3" id="ccc3" hflex="1" visible="false"/>
				        <column label="c4" hflex="1"/>
				    </columns>
				    <rows>
				        <row forEach="${obj}">
				            <label value="0"/>
				            <label value="0"/>
				            <label value="0"/>
				            <label value="0"/>
				            <label value="0"/>
				        </row>
				    </rows>
				    <foot>
				    	<footer label="f0" />
				    	<footer label="f1" />
				    	<footer label="f2" />
				    	<footer label="f3" />
				    	<footer label="f4" />
				    </foot>
				</grid>
			</div>
		</div>
		<div>
			<custom-attributes org.zkoss.zul.nativebar="false"/>
			<button label="show1" onClick="d1.setVisible(true);dd1.setVisible(true);ddd1.setVisible(true)" />
			<button label="show3" onClick="d3.setVisible(true);dd3.setVisible(true);ddd3.setVisible(true)" />
			<div height="300px" width="700px">
				<tree vflex="1" hflex="1">
					<treecols sizable="true">
						<treecol label="c0" id="d0" hflex="2" />
						<treecol label="c1" id="d1" hflex="1" visible="false" />
						<treecol label="c2" id="d2" hflex="1" />
						<treecol label="c3" id="d3" hflex="1" visible="false" />
						<treecol label="c4" id="d4" hflex="1" />
					</treecols>
					<treechildren>
						<treeitem forEach="${obj}" open="false">
							<treerow>
								<treecell label="Row A ${forEachStatus.index}" />
								<treecell label="0.0" />
								<treecell label="0.0" />
								<treecell label="0.0" />
								<treecell label="0.0" />
							</treerow>
							<treechildren>
								<treeitem>
									<treerow>
										<treecell
											label="Row A ${forEachStatus.index} X" />
										<treecell label="0.0" />
										<treecell label="0.0" />
										<treecell label="0.0" />
										<treecell label="0.0" />
									</treerow>
								</treeitem>
								<treeitem>
									<treerow>
										<treecell
											label="Row A ${forEachStatus.index} X" />
										<treecell label="0.0" />
										<treecell label="0.0" />
										<treecell label="0.0" />
										<treecell label="0.0" />
									</treerow>
								</treeitem>
							</treechildren>
						</treeitem>
					</treechildren>
					<treefoot>
			            <treefooter label="ft0" />
			            <treefooter label="ft1" />
			            <treefooter label="ft2" />
			            <treefooter label="ft3" />
			            <treefooter label="ft4" />
			        </treefoot>
				</tree>
			</div>
			<div height="300px" width="700px">
				<listbox vflex="1" hflex="1">
			        <listhead sizable="true">
			            <listheader label="h0" hflex="2" />
			            <listheader label="h1" id="dd1" hflex="1" visible="false" />
			            <listheader label="h2" hflex="1" />
			            <listheader label="h3" id="dd3" hflex="1" visible="false" />
			            <listheader label="h4" hflex="1" />
			        </listhead>
			        <listitem forEach="${obj}">
			            <listcell label="0" />
			            <listcell label="0" />
			            <listcell label="0" />
			            <listcell label="0" />
			            <listcell label="0" />
			        </listitem>
			        <listfoot>
			            <listfooter label="f0"/>
			            <listfooter label="f1"/>
			            <listfooter label="f2"/>
			            <listfooter label="f3"/>
			            <listfooter label="f4"/>
			        </listfoot>
			    </listbox>
			</div>
			<div height="300px" width="700px">
				<grid vflex="1" hflex="1">
				    <columns sizable="true">
				        <column label="c0" hflex="2"/>
				        <column label="c1" id="ddd1" hflex="1" visible="false"/>
				        <column label="c2" hflex="1"/>
				        <column label="c3" id="ddd3" hflex="1" visible="false"/>
				        <column label="c4" hflex="1"/>
				    </columns>
				    <rows>
				        <row forEach="${obj}">
				            <label value="0"/>
				            <label value="0"/>
				            <label value="0"/>
				            <label value="0"/>
				            <label value="0"/>
				        </row>
				    </rows>
				    <foot>
				    	<footer label="f0" />
				    	<footer label="f1" />
				    	<footer label="f2" />
				    	<footer label="f3" />
				    	<footer label="f4" />
				    </foot>
				</grid>
			</div>
		</div>
	</hlayout>
	
	
</zk>

"""  
  runZTL(zscript,
    () => {
      val check = () => {
    	val tree = jq("@tree");
    	val listbox = jq("@listbox");
        val grid = jq("@grid");
        println(tree.eq(0).find("@treecol").last().offsetLeft(), tree.eq(0).find("@treerow").first().find("@treecell").offsetLeft());
        verifyTrue("tree header and content shouldn't misaligned.", 
            tree.eq(0).find("@treecol").last().offsetLeft() == 
              tree.eq(0).find("@treerow").first().find("@treecell").last().offsetLeft());
        verifyTrue("tree header and content shouldn't misaligned.", 
            tree.eq(1).find("@treecol").last().offsetLeft() == 
              tree.eq(1).find("@treerow").first().find("@treecell").last().offsetLeft());
        
        verifyTrue("listbox header and content shouldn't misaligned.", 
            listbox.eq(0).find("@listheader").last().offsetLeft() == 
              listbox.eq(0).find("@listitem").first().find("@listcell").last().offsetLeft());
        verifyTrue("listbox header and content shouldn't misaligned.", 
            listbox.eq(1).find("@listheader").last().offsetLeft() == 
              listbox.eq(1).find("@listitem").first().find("@listcell").last().offsetLeft());
        
        
        verifyTrue("grid header and content shouldn't misaligned.", 
            grid.eq(0).find("@column").last().offsetLeft() == 
              grid.eq(0).find("@row").first().find(".z-row-inner").last().offsetLeft());
        verifyTrue("grid header and content shouldn't misaligned.", 
            grid.eq(1).find("@column").last().offsetLeft() == 
              grid.eq(1).find("@row").first().find(".z-row-inner").last().offsetLeft());
      };
      sleep(3000);
      check();
      
      click(jq("@button").eq(0));
      waitResponse();
      click(jq("@button").eq(2));
      waitResponse();
      check();
      
      click(jq("@button").eq(1));
      waitResponse();
      click(jq("@button").eq(3));
      waitResponse();
      check();
    })
    
  }
}
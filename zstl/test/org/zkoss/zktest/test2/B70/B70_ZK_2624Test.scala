package org.zkoss.zktest.test2.B70

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test
import java.awt.event.KeyEvent
import org.openqa.selenium.Keys
import org.zkoss.ztl.ZKSeleneseTestBase

@Tags(tags = "B70-ZK-2624.zul")
class B70_ZK_2624Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2624.zul

	Purpose:
		
	Description:
		
	History:
		Wed, Feb 25, 2015  2:43:13 PM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
<zscript>Object[] obj = new Object[19]</zscript>
	You should be able to see the paging control for each component.
	<hlayout height="150px">
		<tree mold="paging" pageSize="1" vflex="1" hflex="1">
			<treecols>
				<treecol label="Treecell 16px with paging and pageSize 1" />
			</treecols>
			<treechildren>
				<treeitem forEach="1,2,3,4">
					<treerow>
						<treecell label="Item ${each}" />
					</treerow>
				</treeitem>
			</treechildren>
		</tree>
		<tree mold="paging" pagingPosition="bottom" autopaging="true" vflex="1" hflex="1">
			<treecols>
				<treecol label="Treecell 16px with paging and pageSize 1 and autopaging" />
			</treecols>
			<treechildren>
				<treeitem forEach="${obj}">
					<treerow>
						<treecell label="Item ${each}" />
					</treerow>
				</treeitem>
			</treechildren>
		</tree>
		<grid mold="paging" pagingPosition="bottom" autopaging="true" vflex="1" hflex="1">
			<columns>
				<column label="Grid" />
			</columns>
			<rows>
				<row forEach="1,2,3,4">
					<label value="Item ${each}" />
				</row>
			</rows>
		</grid>
	</hlayout>
</zk>
    
"""  
  runZTL(zscript,
    () => {
      verifyEquals(3, jq(".z-paging").length());
    })
    
  }
}
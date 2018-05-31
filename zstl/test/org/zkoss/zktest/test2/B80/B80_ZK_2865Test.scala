package org.zkoss.zktest.test2.B80

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags

@Tags(tags = "B80-ZK-2865.zul")
class B80_ZK_2865Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B80-ZK-2865.zul

	Purpose:
		
	Description:
		
	History:
		Thu Sep 10 09:14:24 CST 2015, Created by jumperchen

Copyright (C) 2015 Potix Corporation. All Rights Reserved.

-->
<zk>
	<zscript><![CDATA[
	    import org.zkoss.zktest.test2.*;
		TreeModel staticA = new B80_ZK_2865Model("static");
		TreeModel staticB = new B80_ZK_2865Model("staticRows");
		TreeModel staticC = new B80_ZK_2865Model("static");
		TreeModel staticD = new B80_ZK_2865Model("staticRows");
	]]></zscript>
    <label multiline="true">
    The bug is a ZK 8.0.0-FL issue only, if you open each of the following tree node,
    it cannot display the sub-nodes in horizontallly. (i.e. the dom TR tag cannot contain another TR tag, which is a wrong rendering)
    </label>
	<div>
		static model using DefaultTreeModel (both sides expand horizontally)
		<hlayout>
			<tree height="200px" width="400px" model="${staticA}" sclass="reportTree">
				<template name="model">
					<treeitem label="${each.data}" />
				</template>
			</tree>
	
			<tree height="200px" width="400px" model="${staticB}" sclass="reportTree">
				<template name="model">
					<treeitem >
							<!-- show folder  -->
						<treerow>
							<treecell sclass="z-label" label="${each.data}" iconSclass="z-icon-folder-o" />
						</treerow>
					</treeitem>
				</template>
			</tree>
		</hlayout>

		static model using DefaultTreeModel + ZK Bind (the render order changes on the left side, right side renders horizontally)
		<hlayout apply="org.zkoss.bind.BindComposer">
			<tree height="200px" width="400px" model="@init(staticC)" sclass="reportTree">
				<template name="model">
					<treeitem label="@init(each.data)" />
				</template>
			</tree>
	
			<tree height="200px" width="400px" model="@init(staticD)" sclass="reportTree">
				<template name="model">
					<treeitem >
							<!-- show folder  -->
						<treerow>
							<treecell sclass="z-label" label="@init(each.data)" iconSclass="z-icon-folder-o" />
						</treerow>
					</treeitem>
				</template>
			</tree>
		</hlayout>

		static inline tree inside the zul file (no problem)
		<hlayout>
			<tree height="200px" width="400px">
				<treechildren>
					<treeitem label="aaa" open="false">
						<treechildren>
							<treeitem label="aaa-1" open="false">
								<treechildren>
									<treeitem label="aaa-1-1">
									</treeitem>
									<treeitem label="aaa-1-2">
									</treeitem>
								</treechildren>
							</treeitem>
							<treeitem label="aaa-2" open="false">
								<treechildren>
									<treeitem label="aaa-2-1">
									</treeitem>
									<treeitem label="aaa-2-2">
									</treeitem>
								</treechildren>
							</treeitem>
						</treechildren>
					</treeitem>
				</treechildren>
			</tree>
		
			<tree height="200px" width="400px">
				<treechildren>
					<treeitem open="false">
						<treerow>
							<treecell label="aaa"/>
						</treerow>
						<treechildren>
							<treeitem open="false">
								<treerow>
									<treecell label="aaa-1"/>
								</treerow>
								<treechildren>
									<treeitem>
										<treerow>
											<treecell label="aaa-1-1"/>
										</treerow>
									</treeitem>
									<treeitem>
										<treerow>
											<treecell label="aaa-1-2"/>
										</treerow>
									</treeitem>
								</treechildren>
							</treeitem>
							<treeitem open="false">
								<treerow>
									<treecell label="aaa-1"/>
								</treerow>
								<treechildren>
									<treeitem>
										<treerow>
											<treecell label="aaa-2-1"/>
										</treerow>
									</treeitem>
									<treeitem>
										<treerow>
											<treecell label="aaa-2-2"/>
										</treerow>
									</treeitem>
								</treechildren>
							</treeitem>
						</treechildren>
					</treeitem>
				</treechildren>
			</tree>
		</hlayout>
	</div>
</zk>

"""
    runZTL(zscript,
      () => {
        sleep(5000)
        //expand the 1st treerow in the 1st tree
        click(jq(".z-treechildren:eq(0) .z-tree-icon:eq(0)"))
        waitResponse()
        //check that the expanded treerow does not have any nested tr
        verifyFalse(jq(".z-treechildren:eq(0) .z-treerow:eq(0) tr").exists())

        //expand the 1st treerow in the 2nd tree
        click(jq(".z-treechildren:eq(1) .z-tree-icon:eq(0)"))
        waitResponse()
        //check that the expanded treerow does not have any nested tr
        verifyFalse(jq(".z-treechildren:eq(1) .z-treerow:eq(0) tr").exists())

        //expand the 1st treerow in the 3rd tree
        click(jq(".z-treechildren:eq(2) .z-tree-icon:eq(0)"))
        waitResponse()
        //check that the expanded treerow does not have any nested tr
        verifyFalse(jq(".z-treechildren:eq(2) .z-treerow:eq(0) tr").exists())

        //expand the 1st treerow in the 4th tree
        click(jq(".z-treechildren:eq(3) .z-tree-icon:eq(0)"))
        waitResponse()
        //check that the expanded treerow does not have any nested tr
        verifyFalse(jq(".z-treechildren:eq(3) .z-treerow:eq(0) tr").exists())

        //expand the 1st treerow in the 5th tree
        click(jq(".z-treechildren:eq(4) .z-tree-icon:eq(0)"))
        waitResponse()
        //check that the expanded treerow does not have any nested tr
        verifyFalse(jq(".z-treechildren:eq(4) .z-treerow:eq(0) tr").exists())

        //expand the 1st treerow in the 6th tree
        click(jq(".z-treechildren:eq(5) .z-tree-icon:eq(0)"))
        waitResponse()
        //check that the expanded treerow does not have any nested tr
        verifyFalse(jq(".z-treechildren:eq(5) .z-treerow:eq(0) tr").exists())
      })
  }
}
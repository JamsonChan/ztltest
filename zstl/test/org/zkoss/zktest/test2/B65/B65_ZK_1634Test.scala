package org.zkoss.zktest.test2.B65

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase

@Tags(tags = "B65-ZK-1634.zul")
class B65_ZK_1634Test extends ZTL4ScalaTestCase {

  def testClick() = {
    val zscript = """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B65-ZK-1634.zul

	Purpose:

	Description:

	History:
		Mon, Feb 25, 2013  2:42:22 PM, Created by jumperchen

Copyright (C) 2013 Potix Corporation. All Rights Reserved.

-->
<zk>
	<zscript><![CDATA[
	DefaultTreeNode r = new DefaultTreeNode("root", new ArrayList());
	DefaultTreeNode n1 = new DefaultTreeNode("Node 1", new ArrayList());
	DefaultTreeNode n2 = new DefaultTreeNode("Node 2", new ArrayList());
	DefaultTreeNode n3 = new DefaultTreeNode("Node 3", new ArrayList());
	DefaultTreeNode n4 = new DefaultTreeNode("Node 4");
	DefaultTreeNode n5 = new DefaultTreeNode("Node 5");
	r.add(n1);
	n1.add(n2);
	n2.add(n3);
	n3.add(n4);
	DefaultTreeModel model = new DefaultTreeModel(r);
	model.addOpenObject(n1);
	model.addOpenObject(n2);
	model.addOpenObject(n3);

	TreeitemRenderer myRenderer = new org.zkoss.zktest.test2.B65_ZK_1634Renderer();
]]></zscript>
	<label multiline="true">
		1.click on set Node2 Data, the node 2 label should change to New Node 2, and still has child Node3
		2.if the bug is not fixed, you will get exception
	</label>
	<button label="set Node2 Data" onClick='n2.setData("New Node 2")'/>
	<tree model="${model}" itemRenderer="${myRenderer}">
		<treecols visible="false">
			<treecol label="Unit"></treecol>
		</treecols>
		<template name="model">
			<treeitem>
				<treerow>
					<treecell label="${each.data}"/>
				</treerow>
			</treeitem>
		</template>
	</tree>
</zk>

    """

    runZTL(zscript,
      () => {
        click(jq(".z-button:contains(set Node2 Data)"))
        waitResponse()

        verifyTrue("the node 2 label should change to New Node 2", jq(".z-treecell:contains(New Node 2)").exists())
        verifyTrue("still has child Node3", jq(".z-treecell:contains(Node 3)").exists())
        verifyTrue("you should not see any exception dialog", !jq(".z-window-modal").exists())
      })

  }
}

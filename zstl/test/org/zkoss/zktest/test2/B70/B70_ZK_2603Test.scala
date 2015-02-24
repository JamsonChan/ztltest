package org.zkoss.zktest.test2.B70

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test
import java.awt.event.KeyEvent
import org.openqa.selenium.Keys
import org.zkoss.ztl.ZKSeleneseTestBase

@Tags(tags = "B70-ZK-2603.zul")
class B70_ZK_2603Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2603.zul

	Purpose:
		
	Description:
		
	History:
		Tue, Feb 03, 2015  2:20:08 PM, Created by Chunfu

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>

	<zscript><![CDATA[
	import org.zkoss.zul.*;
	import java.util.ArrayList;
	ListModelList mymodel = new ListModelList();
	mymodel.add("aaa");
	mymodel.add("bbb");
	mymodel.add("ccc");
	ListModelList gridmodel = new ListModelList();
	gridmodel.add("aaa");
	gridmodel.add("bbb");
	gridmodel.add("ccc");
	public class MyListbox extends Listbox {
		public void onInitRender() {
			super.onInitRender();
			Clients.log("listbox init " + this.getId());
		}
	}
	public class MyGrid extends Grid {
		public void onInitRender() {
			super.onInitRender();
			Clients.log("grid init " + this.getId());
		}
	}
	
	public class MyTree extends Tree {
		public void onInitRender() {
			super.onInitRender();
			Clients.log("tree init " + this.getId());
		}
	}
]]>
	</zscript>
	<label multiline="true">
	1. you should see "init list1" and "init list2"
	2. click the "detach" button, you should see only "init list1" is added.
	3. click the "attach" button, you should see "init list1" and "init list2" are added.
	 and the content of both listbox are "abc"
	</label>
	<listbox id="list1" model="${mymodel}" use="MyListbox">
		<template name="model">
			<listitem>
				<listcell label="${each}" />
			</listitem>
		</template>
	</listbox>

	<listbox id="list2" model="${mymodel}" use="MyListbox">
		<template name="model">
			<listitem>
				<listcell label="${each}" />
			</listitem>
		</template>
	</listbox>
	<button label="detach">
		<attribute name="onClick">
			Component list2 = list2;
			list2.detach();
			mymodel.clear();
			mymodel.add("ddd");
			mymodel.add("eee");
			mymodel.add("fff");
		</attribute>
	</button>
	<button label="attach">
		<attribute name="onClick">
			list2.setPage(self.getPage());
			mymodel.clear();
			mymodel.add("abc");
			mymodel.add("abc");
			mymodel.add("abc");
		</attribute>
	</button>
	<grid id="grid1" model="${gridmodel}" use="MyGrid">
		<rows>
			<template name="model">
				<row>
					<label value="${each}" />
				</row>
			</template>
		</rows>
	</grid>

	<grid id="grid2" model="${gridmodel}" use="MyGrid">
		<rows>
			<template name="model">
				<row>
					<label value="${each}" />
				</row>
			</template>
		</rows>
	</grid>
	<button label="detach">
		<attribute name="onClick">
			Component grid2 = grid2;
			grid2.detach();
			gridmodel.clear();
			gridmodel.add("ddd");
			gridmodel.add("eee");
			gridmodel.add("fff");
		</attribute>
	</button>
	<button label="attach">
		<attribute name="onClick">
			grid2.setPage(self.getPage());
			gridmodel.clear();
			gridmodel.add("abc");
			gridmodel.add("abc");
			gridmodel.add("abc");
		</attribute>
	</button>
	
	<zscript><![CDATA[		
		DefaultTreeNode r = new DefaultTreeNode("root",new ArrayList());
		DefaultTreeNode n1 = new DefaultTreeNode("Node 1",new ArrayList());
		DefaultTreeNode n2 = new DefaultTreeNode("Node 2",new ArrayList());
		DefaultTreeNode n3 = new DefaultTreeNode("Node 3",new ArrayList());
		DefaultTreeNode n4 = new DefaultTreeNode("Node 4");
		
		r.add(n1);
		n1.add(n2);
		n2.add(n3);
		n3.add(n4);
		           
		DefaultTreeModel model = new DefaultTreeModel(r);
	]]></zscript>
	<tree id="tree1" model="${model}" use="MyTree">
		<template name="model">
			<treeitem>
				<treerow>
					<treecell label="${each.data}"/>
				</treerow>
			</treeitem>
		</template>
	</tree>
	<tree id="tree2" model="${model}" use="MyTree">
		<template name="model">
			<treeitem>
				<treerow>
					<treecell label="${each.data}"/>
				</treerow>
			</treeitem>
		</template>
	</tree>
	<button label="detach">
		<attribute name="onClick">
			Component tree2 = tree2;
			tree2.detach();
			tree1.setModel(model);
		</attribute>
	</button>
	<button label="attach">
		<attribute name="onClick">
			tree2.setPage(self.getPage());
		</attribute>
	</button>
</zk>
    
"""  
  runZTL(zscript,
    () => {
      var btns = jq("@button");
      var it = btns.iterator();
      while (it.hasNext()) {
        click(it.next());
        waitResponse();
      }
      
      var result = "listbox init list1\n" +
    		  		"listbox init list2\n" +
					"grid init grid1\n" +
					"grid init grid2\n" +
					"tree init tree1\n" +
					"tree init tree2\n" +
					"listbox init list1\n" +
					"listbox init list1\n" +
					"listbox init list2\n" +
					"grid init grid1\n" +
					"grid init grid1\n" +
					"grid init grid2\n" +
					"tree init tree1\n" +
					"tree init tree2";
      verifyEquals(jq("#zk_log").eval("val()").trim(), result);
    })
    
  }
}
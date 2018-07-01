package org.zkoss.zktest.test2.B70

import org.zkoss.ztl.annotation.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test

@Tags(tags = "B70-ZK-2179.zul")
class B70_ZK_2179Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """<zk>
	<div>
		swipe down the row, treerow, listitem, then viewport should be changed
	</div>
	<div>listbox context</div>
	<listbox height="100px">
		<listitem forEach="1,2,3,4,5,6" context="popup" label="123" />
	</listbox>
	<div>listbox dbclick</div>
	<listbox height="100px">
		<listitem forEach="1,2,3,4,5,6" onDoubleClick="alert(1)" label="123" />
	</listbox>
	<div>grid context</div>
	<grid height="100px">
		<rows>
			<row forEach="1,2,3,4,5,6" context="popup">123</row>
		</rows>
	</grid>
	<div>grid dbclick</div>
	<grid height="100px">
		<rows>
			<row forEach="1,2,3,4,5,6" onDoubleClick="alert(1)">123</row>
		</rows>
	</grid>
	<div>tree context</div>
	<tree height="100px">
		<treechildren>
			<treeitem forEach="1,2,3,4,5,6">
				<treerow context="popup">
					<treecell>123</treecell>
				</treerow>
			</treeitem>
		</treechildren>
	</tree>
	<div>tree dbclick</div>
	<tree height="100px">
		<treechildren>
			<treeitem forEach="1,2,3,4,5,6">
				<treerow onDoubleClick="alert(1)">
					<treecell>123</treecell>
				</treerow>
			</treeitem>
		</treechildren>
	</tree>
	<popup id="popup">popup</popup>
</zk>"""  
  runZTL(zscript,
    () => {
      0 to 1 foreach { index =>
        val lb = jq(".z-listbox").eq(index)
        val gd = jq(".z-grid").eq(index)
        val tree = jq(".z-tree").eq(index)
        var transform = jq(lb.toWidget().$n("cave")).css("transform")
        
      	swipeUp(lb.find(".z-listitem"), 50)
      	waitResponse()
      	verifyTrue("then viewport should be changed", transform != jq(lb.toWidget().$n("cave")).css("transform"))
      	
      	transform = jq(gd.toWidget().$n("cave")).css("transform")
      	swipeUp(gd.find(".z-row"), 50)
      	waitResponse()
      	verifyTrue("then viewport should be changed", transform != jq(gd.toWidget().$n("cave")).css("transform"))
      	
      	transform = jq(tree.toWidget().$n("cave")).css("transform")
      	swipeUp(tree.find(".z-treerow"), 50)
      	waitResponse()
      	verifyTrue("then viewport should be changed", transform != jq(tree.toWidget().$n("cave")).css("transform"))
      }
      
    })
    
  }
}
/* B50_2947988Test.scala

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Fri Oct 14 15:28:40 CST 2011 , Created by benbai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zktest.test2.B50

import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags
import org.zkoss.ztl.unit.Widget

/**
  * A test class for bug 2947988
  *
  * @author benbai
  *
  */
@Tags(tags = "B50-2947988.zul,A,E,Tree,Model")
class B50_2947988Test extends ZTL4ScalaTestCase {

  def testClick() = {
    val zscript =
      """

			<zk>
			
			    <zscript><![CDATA[
			        java.util.ArrayList children = new java.util.ArrayList();
			        children.add(new DefaultTreeNode("1", new java.util.ArrayList()));
			       DefaultTreeModel model = new DefaultTreeModel(new DefaultTreeNode("ROOT",children));
			
			        void removeItem () {
						testTree.setModel(null);
			        }
			    ]]></zscript>
			    <tree id="testTree" model="${model}"/>
			    <button id="add" label="Click me shouldn't have any error." onClick="removeItem()"/>
			
			
			</zk>

    """
    runZTL(zscript,
      () => {
        var add: Widget = engine.$f("add");

        click(add);
        waitResponse();
        verifyFalse(jq(".z-error").exists());
      }
    );

  }
}
/* B50_ZK_528Test.scala

	Purpose:

	Description:

	History:
		Wed Oct 26 15:49:58 TST 2011, Created by jumperchen

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

This program is distributed under LGPL Version 3.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
package org.zkoss.zktest.test2.B50

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

/**
  *
  * @author jumperchen
  */
@Tags(tags = "B50-ZK-528.zul,A,E,Grid,Listbox,EmptyMessage")
class B50_ZK_528_1Test extends ZTL4ScalaTestCase {
  @Test
  def testListboxCase() = {
    val zscript =
      """
			<div>
				Listbox
				<listbox id="list" emptyMessage="Empty Message">
					<listhead id="lh">
						<listheader label="Column1"/>
						<listheader label="if you can see this, that is a bug" visible="false"/>
					</listhead>
				</listbox>
				<button label="add row">
					<attribute name="onClick">
						list.appendChild(new Listitem("test"));
					</attribute>
				</button>
				<button label="clean rows" onClick="list.getItems().clear()"/>
				<button label="add column">
					<attribute name="onClick">
						lh.appendChild(new Listheader("test"));
					</attribute>
				</button>
				<button label="clean columns" onClick="lh.getChildren().clear()"/>
			</div>
		"""
    runZTL(zscript, () => {

      val emp = jq(".z-listbox").toWidget().$n("empty")
      val hiddenCol = jq("@listbox").find("col[id*=hdfaker][style*=collapse]")
      val column = jq("@listbox").find(".z-listheader")
      verifyEquals(column.length() - hiddenCol.length(), 1);
      verifyNotContains(hiddenCol.attr("id"), jq(".z-listheader").attr("id"))
      verifyTrue(jq(emp).isVisible());
      verifyEquals("Empty Message", jq(emp).text());

      click(jq("@button:eq(0)"));
      waitResponse();
      verifyFalse(jq(emp).isVisible());

      click(jq("@button:eq(1)"));
      waitResponse();
      verifyTrue(jq(emp).isVisible());

      click(jq("@button:eq(2)"));
      click(jq("@button:eq(2)"));
      waitResponse();
      verifyEquals(column.length() - hiddenCol.length(), 3);
      verifyTrue(jq(emp).isVisible());

      click(jq("@button:eq(3)"));
      waitResponse();
      verifyTrue((column.length() - hiddenCol.length()) == 0);
      verifyTrue(jq(emp).isVisible());
    })
  }
}
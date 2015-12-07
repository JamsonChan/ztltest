/* B70_ZK_2722Test.scala

	Purpose:
		
	Description:
		
	History:
		Wed Oct  7 14:25:04 CST 2015, Created by chunfu

Copyright (C)  2015 Potix Corporation. All Rights Reserved.

This program is distributed under LGPL Version 3.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
package org.zkoss.zktest.test2.B70

import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags;

/**
 * 
 * @author chunfu
 */
@Tags(tags = "")
class B70_ZK_2722Test extends ZTL4ScalaTestCase {
	def testCase() = {
		val zscript = """
		<?xml version="1.0" encoding="UTF-8"?>

		<!--
B70-ZK-2722.zul

	Purpose:

	Description:

	History:
		Fri, May 29, 2015  11:30:29 PM, Created by Jameschu

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
			<zk>
				<zscript><![CDATA[
		ListModelList model = new ListModelList();

		model.add("a");
		//model.add("b");

		public void clear(){
			grid.getModel().clear();
		}
	]]>
				</zscript>
				<label multiline="true">
					1. click 'clear' and you should see "a" disappeared with no Exceptions
				</label>
				<custom-attributes org.zkoss.zul.grid.rod = "true"/>
				<grid id="grid" model="${model}" mold="paging" autopaging="true" pageSize="3">
					<template name="model">
						<row>
							<label value="${each}" />
						</row>
					</template>
				</grid>
				<button onClick="clear()">clear</button>
			</zk>
"""
		runZTL(zscript, () => {
			var btn = jq("@button");
			click(btn);
			waitResponse();
			verifyEquals(1, jq("@label").length());
			verifyNotEquals("a", jq("@label").text());
			verifyEquals(0, jq(".z-messagebox-error").length());

		})
	}
}

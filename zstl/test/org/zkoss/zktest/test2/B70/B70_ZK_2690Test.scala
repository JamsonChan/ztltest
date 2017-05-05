/* B70_ZK_2690Test.scala

	Purpose:
		
	Description:
		
	History:
		Wed Oct 14 15:16:49 CST 2015, Created by chunfu

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
@Tags(tags = "B70-ZK-2690.zul")
class B70_ZK_2690Test extends ZTL4ScalaTestCase {
	def testCase() = {
		val zscript = """
<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2690.zul

	Purpose:

	Description:

	History:
		Thu Jun  4 14:55:11 CST 2015, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk xmlns:h="xhtml">
Please scroll the frozen scrollbar to right, and then scroll it back to left.
The hidden column of the grid should be scrolled back too. (IE only)
	<h:table>
		<!--
		-->
		<h:tr>
			<h:td>
				<grid sizedByContent="true">
					<rows>
						<row>
							<label value="person 1" />
							<label value="abc" />
							<label value="123" />
							<label value="1" />
							<label value="2" />
							<label value="3" />
							<label value="" />
							<label value="" />
							<label value="" />
						</row>
						<row>
							<label value="person 1" />
							<label value="abc" />
							<label value="123" />
							<label value="1" />
							<label value="2" />
							<label value="3" />
							<label value="" />
							<label value="" />
							<label value="" />
						</row>
					</rows>
					<auxhead>
						<auxheader colspan="3" />
						<auxheader label="2015" colspan="2" />
						<auxheader label="2016" colspan="2" />
						<auxheader label="2015" />
						<auxheader label="2017" />
					</auxhead>
					<auxhead>
						<auxheader colspan="3" />
						<auxheader label="Task 1" />
						<auxheader label="Task 2" />
						<auxheader label="Task 3" />
						<auxheader label="Task 4" />
						<auxheader label="Task 5" />
						<auxheader label="Task 6" />
					</auxhead>
					<frozen columns="3" />
					<columns sizable="true">
						<column width="200px" label="Name" />
						<column width="100px" label="Anfangsbuchstabe" />
						<column width="100px" label="Abteilung" />
						<column width="90px" />
						<column width="90px" />
						<column width="90px" />
						<column width="90px" />
						<column width="90px" />
						<column width="90px" />
					</columns>
				</grid>
			</h:td>
		</h:tr>
	</h:table>
</zk>


		"""
runZTL(zscript, () => {
			var grid = jq("@grid");
			nativeFrozenScroll(grid, 300);
			waitResponse()
			verifyTrue(jq(".z-auxheader").eq(1).width() < 1)
			nativeFrozenScroll(grid, -300);
			waitResponse()
			verifyTrue(jq(".z-auxheader").eq(1).width() > 1)
		})
	}
}

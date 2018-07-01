package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl._
import org.zkoss.ztl.unit._

@Tags(tags = "B70-ZK-2825.zul")
class B70_ZK_2825Test extends ZTL4ScalaTestCase {
  @Test
  def testClick() = {
    val zscript =
      """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2825.zul

	Purpose:
		
	Description:
		
	History:
		Tue, Jul 21, 2015  14:10:34 PM, Created by jameschu

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
	<script type="text/javascript" defer="true"><![CDATA[
		var arr = ['0', '1', '2'], content = '';
		for (var i in arr) {
			content += i + '\n';
		}
		zk.Widget.$('$lbl').setValue(content);
	]]></script>

	<label> Should only see 0, 1, 2 below:</label>
	<separator />
	<label id="lbl" multiline="true"></label>
</zk>

"""
    runZTL(zscript,
      () => {
        verifyEquals("0\n1\n2\n", jq("$lbl").text())
      })
  }
}
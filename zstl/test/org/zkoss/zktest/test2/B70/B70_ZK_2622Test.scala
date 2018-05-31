package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags

@Tags(tags = "B70-ZK-2622.zul")
class B70_ZK_2622Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """
<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2622.zul

	Purpose:
		
	Description:
		
	History:
		Tue, Feb 10, 2015  3:12:00 PM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
  <window border="normal" title="hello">
  	You should not see any JS error, and the same as after click the button.
    <include id="inc" src="test2/B70-ZK-2622_1.zul"/>
    <button id="btn" label="Click Me To Reproduce The Bug" onClick='inc.src=null; inc.src="test2/B70-ZK-2622_1.zul"' />
  </window>
</zk>
"""
    runZTL(zscript,
      () => {
        var result = "hello\n" +
          "</Script>";
        verifyEquals(jq("#zk_log").eval("val()").trim(), result);
        var btn = jq("@button");
        click(btn);
        waitResponse(true);
        result = "hello\n" +
          "</Script>\n" +
          "hello\n" +
          "</Script>";

        verifyEquals(jq("#zk_log").eval("val()").trim(), result);
      })

  }
}
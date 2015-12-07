package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags

@Tags(tags = "B70-ZK-2680.zul")
class B70_ZK_2680Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """
<?xml version="1.0" encoding="UTF-8"?>
<!--
B70-ZK-2680.zul

  Purpose:

  Description:

  History:
    Mon Jun 15 17:48:44 CST 2015, Created by chunfu

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
  <label multiline="true">
  1. open nav and open the first folder icon
  2. mouse out and enter again
  3. the open status should be the same as last mouse out
  </label>
  <navbar orient="vertical" width="45px" collapsed="true">
    <nav label="static" iconSclass="z-icon-question-circle" >
      <nav label="1">
        <nav iconSclass="z-icon-folder">
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
        </nav>
        <nav iconSclass="z-icon-folder">
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
        </nav>
        <nav iconSclass="z-icon-folder">
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
        </nav>
        <nav iconSclass="z-icon-folder">
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
          <navitem iconSclass="z-icon-circle-o"/>
        </nav>
      </nav>
    </nav>
  </navbar>
</zk>
"""  
  runZTL(zscript,
    () => {
      val icon = jq(".z-nav")
      click(icon)
      waitResponse()
      click(jq(".z-nav-popup .z-nav").first())
      waitResponse()
      mouseOut(icon)
      waitResponse()
      mouseOver(icon)
      waitResponse()
      verifyTrue(jq(".z-nav-popup .z-nav").hasClass("z-nav-open"))
    })
  }
}
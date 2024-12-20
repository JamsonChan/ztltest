/* B30_1914105Test.scala

	Purpose:
		
	Description:
		
	History:
		Wed Oct 12 09:45:50 TST 2011, Created by jumperchen

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

This program is distributed under LGPL Version 3.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
package org.zkoss.zktest.test2.B30

import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

/**
  *
  * @author jumperchen
  */
@Tags(tags = "B30-1914105.zul,B,E,Timebox")
class B30_1914105Test extends ZTL4ScalaTestCase {
  def testCase() = {
    runZTL(() => {
      val tb = engine.$f("tb")
      val ck1 = engine.$f("ck1")
      val ck2 = engine.$f("ck2")
      sendKeys(tb.$n("real"), "12:00:00")
      waitResponse()
      verifyContains("value should start with 12:00:00", jq(tb.$n("real")).`val`(), "12:00:00")

      // test readonly
      click(ck1.$n("real"))
      waitResponse()
      sendKeys(tb.$n("real"), "12:00:01")
      verifyEquals("value should be 12:00:00", jq(tb.$n("real")).`val`(), "12:00:00")

      click(jq(".z-timebox").toWidget().$n("btn-up"));

      val value = jq(tb.$n("real")).`val`()

      verifyNotEquals("value should not be 12:00:00", value, "12:00:00")

      // test disabled
      click(ck2.$n("real"))
      waitResponse()

      click(jq(".z-timebox").toWidget().$n("btn-up"));

      verifyEquals("value should be " + value, jq(tb.$n("real")).`val`(), value)
    })
  }
}

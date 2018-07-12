package org.zkoss.zktest.test2.F80

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "F80-ZK-2727.zul")
class F80_ZK_2727Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """

<?xml version="1.0" encoding="UTF-8"?>

<!--
F80-ZK-2727.zul

  Purpose:
    
  Description:
    
  History:
    Thu, Apr 23, 2015  4:24:46 PM, Created by Chunfu

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
<zscript><![CDATA[
public void append(Scrollview sv, int pos, boolean outBound) {
  if (outBound && pos > 0) {
    Window w = new Window("window end", "normal", false);
    w.setHeight("250px");
    w.setWidth("250px");
    sv.appendChild(w);
  } else if (outBound && pos == 0) {
    Window w = new Window("window top", "normal", false);
    w.setHeight("250px");
    w.setWidth("250px");
    sv.insertBefore(w, sv.getFirstChild());
  }
}
public String toggleOrient(Scrollview sv) {
  String orient = sv.getOrient();
  return "vertical".equals(orient) ? "horizontal" : "vertical";
}
]]></zscript>
<label multiline="true">
1. scroll to the bottom
2. you should see "window end" appends to the last
</label>
<scrollview id="sv" height="500px" width="800px"
    onScroll="append(self, event.pos, event.outOfBound)" onScrolling="" orient="horizontal">
    
  <window title="window1" border="normal" width="250px" height="250px">
        This is Window 1
    </window>
    <window title="window2" border="normal" width="250px" height="250px">
        This is Window 2
    </window>
    <window title="window2" border="normal" width="250px" height="250px">
        This is Window 2
    </window>
    <window title="window2" border="normal" width="250px" height="250px">
        This is Window 2
    </window>
    <window title="window2" border="normal" width="250px" height="250px">
        This is Window 2
    </window>
    <window title="window2" border="normal" width="250px" height="250px">
        This is Window 2
    </window>
    <window title="window2" border="normal" width="250px" height="250px">
        This is Window 2
    </window>
    <window title="window2" border="normal" width="250px" height="250px">
        This is Window 2
    </window>
    <window title="window2" border="normal" width="250px" height="250px">
        This is Window 2
    </window>   
</scrollview>
<button onClick='sv.setOrient(toggleOrient(sv))'>toggle orient</button>

</zk>

"""
    runZTL(zscript,
      () => {
        var sv = jq("@scrollview");
        var count = jq("@window").length();
        horScrollNoBody(sv, 1);
        verifyTrue(jq("@window").length() > count);

        count = jq("@window").length();
        click(jq(".z-button"));
        waitResponse()
        verScroll(sv, 1);
        waitResponse()
        verifyTrue(jq("@window").length() > count);
      })

  }
}
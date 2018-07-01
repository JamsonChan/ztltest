package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "B70-ZK-2290.zul")
class B70_ZK_2290Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2290.zul

	Purpose:
		
	Description:
		
	History:
		Tue, May 13, 2014 12:59:08 PM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
  <window width="100%" height="99%" style="padding: 0;"
      title="Some Screen" >
    1. Please resizing a listbox column so the other columns get pushed out of the viewport.
    <separator/>
    2. If no horizontal scrollbar, that's a bug.
    
    <listbox mold="paging" autopaging="true"  vflex="true">
    	<custom-attributes org.zkoss.zul.nativebar="true"/>
      <listhead sizable="true">
        <listheader label="Someheader1" width="100px" sort="auto"/>
        <listheader label="Someheader2" width="100px" sort="auto"/>
        <listheader label="Someheader3" width="100px" sort="auto"/>
        <listheader label="Someheader4" width="100px" sort="auto"/>
        <listheader label="Someheader5" width="100px" sort="auto"/>
        
        <listheader label="Someheader6" width="100px" sort="auto"/>
        <listheader label="Someheader7" width="100px" sort="auto"/>
        <listheader label="Someheader8" width="100px" sort="auto"/>
        <listheader label="Someheader9" width="100px" sort="auto"/>
        <listheader label="Someheader10" width="100px" sort="auto"/>
        
        <listheader label="Someheader11" width="100px" sort="auto"/>
        <listheader label="Someheader12" width="100px" sort="auto"/>
        <listheader label="Someheader13" width="100px" sort="auto"/>
        <listheader label="Someheader14" width="100px" sort="auto"/>
        <listheader label="Someheader15" width="100px" sort="auto"/>
        
      </listhead>  
    </listbox>
  </window>
</zk>
"""
    runZTL(zscript,
      () => {
        // can't work correctly in ie9, ie10
        // ignore both of them instead.
        val headers = jq(".z-listheader");
        val h1 = headers.eq(1);
        val h6 = headers.eq(6);
        mouseMoveAt(h1, h1.width() + ",10");
        waitResponse();
        mouseDownAt(h1, h1.width() + ",10");
        waitResponse();
        mouseMoveAt(h6, h6.width() + ",10");
        waitResponse();
        mouseUpAt(h6, h6.width() + ",10");
        waitResponse();

        verifyTrue("browser should show scrollbar.", hasHScrollbar(jq(".z-listbox-body")));
      })

  }
}
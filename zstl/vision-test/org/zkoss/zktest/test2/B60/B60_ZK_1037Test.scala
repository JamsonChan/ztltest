package org.zkoss.zktest.test2.B60

import org.zkoss.ztl.annotation.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test

@Tags(tags = "B60-ZK-1037.zul")
class B60_ZK_1037Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript = """<?xml version="1.0" encoding="UTF-8"?>

<!--
B60-ZK-1037.zul

	Purpose:
		
	Description:
		
	History:
		Mon Apr  16 16:50:38 TST 2012, Created by benbai

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

-->
<zk>
	<div>You should see four listbox below.</div>
	<div>The 1st, 2nd and 3rd listbox do not have horizontal scroll bar.</div>
	<div>Click the button 'test 1'.</div>
	<div>You should see an empty message added to 1st listbox and the horizontal scroll bar appeared.</div>
	<div>Click the button 'test 2'.</div>
	<div>You should see the empty message removed from 1st listbox and the horizontal scroll bar disappeared.</div>
	<div>Click the button 'test 3'.</div>
	<div>You should see a listitem and the horizontal scroll bar appear in 2nd listbox.</div>
	<div>Click the button 'test 4'.</div>
	<div>You should see the listitem and the horizontal scroll bar of 2nd listbox disappeared.</div>
	<div>Click the button 'test 5'.</div>
	<div>You should see a listitem and the horizontal scroll bar appear in 3rd listbox.</div>
	<div>Click the button 'test 6'.</div>
	<div>You should see the listitem and the horizontal scroll bar of 3rd listbox disappeared.</div>
	<div>Click the button 'test 7'.</div>
	<div>You should see the listitem and the horizontal scroll bar of 4th listbox disappeared.</div>
	<zscript>
		Listitem li = new Listitem();
		for (int i = 0; i != 3; i++) {
			new Listcell("item").setParent(li);
		}
	</zscript>
	<div width="300px">
		<button onClick='lb1.setEmptyMessage(" is empty");' label="test 1" />
		<button onClick='lb1.setEmptyMessage(null);' label="test 2" />
		<button onClick='li1.setVisible(true);' label="test 3" />
		<button onClick='li1.setVisible(false);' label="test 4" />
		<button label="test 5">
			<attribute name="onClick">
				li.setParent(lb3);
			</attribute>
		</button>
		<button label="test 6">
			<attribute name="onClick">
				li.setParent(null);
			</attribute>
		</button>
		<button onClick='li2.setParent(null);' label="test 7" />
		<listbox id="lb1">
			<listhead>
				<listheader label="col 1" width="150px" />
				<listheader label="col 2" width="150px" />
				<listheader label="col 3" width="100px" />
			</listhead>
		</listbox>
		<listbox>
			<listhead>
				<listheader label="col 1" width="150px" />
				<listheader label="col 2" width="150px" />
				<listheader label="col 3" width="100px" />
			</listhead>
			<listitem id="li1" visible="false">
				<listcell label="item"/>
				<listcell label="item"/>
				<listcell label="item"/>
			</listitem>
		</listbox>
		<listbox id="lb3">
			<listhead>
				<listheader label="col 1" width="150px" />
				<listheader label="col 2" width="150px" />
				<listheader label="col 3" width="100px" />
			</listhead>
		</listbox>

		<listbox>
			<listhead>
				<listheader label="col 1" width="150px" />
				<listheader label="col 2" width="150px" />
				<listheader label="col 3" width="100px" />
			</listhead>
			<listitem id="li2">
				<listcell label="item"/>
				<listcell label="item"/>
				<listcell label="item"/>
			</listitem>
		</listbox>
	</div>
</zk>"""
    runZTL(zscript,
      () => {

        click(jq(".z-button:contains(1)"))
        waitResponse()
        verifyImage()
        
        click(jq(".z-button:contains(2)"))
        waitResponse()
        verifyImage()

        click(jq(".z-button:contains(3)"))
        waitResponse()
        verifyImage()

        click(jq(".z-button:contains(4)"))
        waitResponse()
        verifyImage()

        click(jq(".z-button:contains(5)"))
        waitResponse()
        verifyImage()

        click(jq(".z-button:contains(6)"))
        waitResponse()
        verifyImage()

        click(jq(".z-button:contains(7)"))
        waitResponse()
        verifyImage()

      })

  }
}
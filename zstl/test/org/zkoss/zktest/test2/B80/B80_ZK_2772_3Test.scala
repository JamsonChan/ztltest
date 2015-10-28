package org.zkoss.zktest.test2.B80

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags
import org.zkoss.ztl.util.ConfigHelper
import org.zkoss.ztl.JQuery

@Tags(tags = "B80-ZK-2772-3.zul")
class B80_ZK_2772_3Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B80-ZK-2772-3.zul

	Purpose:
		
	Description:
		
	History:
		Tue Sep 22 13:43:49 CST 2015, Created by jumperchen

Copyright (C) 2015 Potix Corporation. All Rights Reserved.

-->
<zk>
    <vlayout>

        <label multiline="true">
            Grid Test 3, test steps as follows.
            1. Sorting the Column 6 by clicking its title. (The column width for each column should stay the same as before)
            2. Scroll to the end of the right for the frozen column, and then sorting the Column 10.
            All the column width should stay as the same as before.
            3. Resize anyone of the columns (not the frozen columns), and sorting its column, the width of each column should stay the same as before)
        </label>
        <grid apply="org.zkoss.bind.BindComposer"
              viewModel="@id('vm') @init('org.zkoss.zktest.test2.B80_ZK_2772VM')"
              height="400px"
              span="true"
              model="@load(vm.customers)">
            <auxhead>
                <auxheader label="First 5 Columns" colspan="5"/>
                <auxheader label="Next 5 Columns" colspan="5"/>
            </auxhead>
            <columns sizable="true" menupopup="auto">
                <column hflex="min" label="Column 1" sort="auto(name0)"/>
                <column hflex="min" label="Column 2" sort="auto(name1)"/>
                <column hflex="min" label="Column 3" sort="auto(name2)"/>
                <column hflex="min" label="Column 4" sort="auto(name3)"/>
                <column hflex="min" label="Column 5" sort="auto(name4)"/>
                <column label="Column 6" sort="auto(name5)"/>
                <column label="Column 7" sort="auto(name6)"/>
                <column label="Column 8" sort="auto(name7)"/>
                <column label="Column 9" sort="auto(name8)"/>
                <column label="Column 10" sort="auto(name9)"/>
            </columns>
            <frozen columns="5"/>
            <template name="model">
                <row>
                    <cell>
                        <label value="@load(each.name0)"/>
                    </cell>
                    <cell>
                        <label value="@load(each.name1)"/>
                    </cell>
                    <cell>
                        <label value="@load(each.name2)"/>
                    </cell>
                    <cell>
                        <label value="@load(each.name3)"/>
                    </cell>
                    <cell>
                        <label value="@load(each.name4)"/>
                    </cell>
                    <cell>
                        <label value="@load(each.name5)"/>
                    </cell>
                    <cell>
                        <label value="@load(each.name6)"/>
                    </cell>
                    <cell>
                        <label value="@load(each.name7)"/>
                    </cell>
                    <cell>
                        <label value="@load(each.name8)"/>
                    </cell>
                    <cell>
                        <label value="@load(each.name9)"/>
                    </cell>
                </row>
            </template>
            <foot>
                <footer/>
                <footer/>
                <footer/>
                <footer/>
                <footer/>
                <footer label="Footer"/>
                <footer label="Footer"/>
                <footer label="Footer"/>
                <footer label="Footer"/>
                <footer label="Footer"/>
            </foot>
        </grid>
    </vlayout>
</zk>

""" 
  runZTL(zscript,
    () => {
      //save the original column width
      var originalWidths = List[Int]()
      var jqi = jq(".z-column").iterator()
      while(jqi.hasNext()) {
        originalWidths :+= jqi.next().width()
      }
      //sort column 6
      click(jq(".z-column").eq(5))
      waitResponse()
      //check the new column width
      jqi = jq(".z-column").iterator()
      println(originalWidths.size)
      for (width <- originalWidths) {
    	  verifyEquals(width, jqi.next())
      }
      //scroll to right
      nativeFrozenScroll(jq(".z-grid"), 400)
      waitResponse()
      //sort the last column
      click(jq(".z-column").last())
      waitResponse()
      //check the new column width remains the same
      jqi = jq(".z-column").iterator()
      for (width <- originalWidths) {
    	  verifyEquals(width, jqi.next())
      }
      //scroll to left
      nativeFrozenScroll(jq(".z-grid"), -400)
      waitResponse()
      //resize column 7
      val column7 = jq(".z-column").eq(6)
      val width_0 = column7.outerWidth()
      mouseMoveAt(column7, column7.outerWidth() + "," + (column7.outerHeight() / 2))
      waitResponse()
      mouseDownAt(column7, column7.outerWidth() + "," + (column7.outerHeight() / 2))
      waitResponse()
      mouseMoveAt(column7, (column7.outerWidth() + 100) + "," + (column7.outerHeight() / 2))
      waitResponse()
      mouseUp(column7)
      waitResponse()
      //save column width
      var newWidths = List[Int]()
      jqi = jq(".z-column").iterator()
      while(jqi.hasNext()) {
    	  newWidths :+= jqi.next().width()
      }
      //sort column 7
      click(column7)
      waitResponse()
      //check width should be the same
      jqi = jq(".z-column").iterator()
      for (width <- newWidths) {
        println(width)
        verifyEquals(width, jqi.next())
      }
    })
  }
}
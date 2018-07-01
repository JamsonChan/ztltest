package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.annotation.Tags

@Tags(tags = "B70-ZK-2348.zul")
class B70_ZK_2348Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2348.zul

	Purpose:
		
	Description:
		
	History:
		Wed, Jul 16, 2014  4:41:33 PM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
	You should see only one listheader(auxheader), if you see two listheaders that's a bug
	<listbox width="200px">
		<listhead>
			<listheader hflex="1" />
			<listheader hflex="2" />
			<listheader hflex="1" />
		</listhead>
		<auxhead>
			<auxheader colspan="3">
				auxheader (listheaders hidden)
			</auxheader>
		</auxhead>
		<listitem>
			<listcell>hflex 1</listcell>
			<listcell>hflex 2</listcell>
			<listcell>hflex 1</listcell>
		</listitem>
		<listitem>
			<listcell>hflex 1</listcell>
			<listcell>hflex 2</listcell>
			<listcell>hflex 1</listcell>
		</listitem>
	</listbox>
</zk>

"""
    runZTL(zscript,
      () => {
        verifyFalse("listhead should be invisible", jq("@listhead").isVisible());
      })

  }
}
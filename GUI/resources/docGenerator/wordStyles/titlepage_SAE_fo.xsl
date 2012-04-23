<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:fo="http://www.w3.org/1999/XSL/Format"
  xmlns:db="http://docbook.org/ns/docbook"
  version="1.0">

  <xsl:template name="saeheader.table">
    <!-- Eventually move carefully crafted SAE boilerplate here from JSS_db.xml -->
  </xsl:template>

  <xsl:template name="saefooter.table">
    <fo:table border-before-width.conditionality="retain"
      border-collapse="collapse" border-left-style="none"
      border-right-style="none" border-top-style="solid"
      border-bottom-style="none" border-top-width="0.5pt"
      border-top-color="black" table-layout="fixed" width="100%"
      font-size="7pt">
      <fo:table-column column-number="1" column-width="1.75in"/>
      <fo:table-column column-number="2" column-width="0.375in"/>
      <fo:table-column column-number="3" column-width="2.175in"/>
      <fo:table-column column-number="4" column-width="3.2in"/>
      <fo:table-body start-indent="0pt" end-indent="0pt">
        <fo:table-row border-top-width="3pt">
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            border-bottom-style="none"
            text-align="left"
            number-columns-spanned="4">
            <fo:block>
              <fo:block space-before.optimum="3pt" space-before.minimum="3pt" space-before.maximum="3pt">
                SAE Technical Standards Board Rules provide that: “This report is published by SAE to advance the state of technical and engineering sciences.  The use of this report is entirely voluntary, and its applicability and suitability for any particular use, including any patent infringement arising therefrom, is the sole responsibility of the user.”
              </fo:block>
              <fo:block space-before.optimum="3pt" space-before.minimum="3pt" space-before.maximum="3pt">
                SAE reviews each technical report at least every five years at which time it may be reaffirmed, revised, or cancelled.  SAE invites your written comments and suggestions.
              </fo:block>
              <fo:block space-before.optimum="3pt" space-before.minimum="3pt" space-before.maximum="3pt">
                Copyright © 2009 SAE International
              </fo:block>
              <fo:block space-before.optimum="3pt" space-before.minimum="3pt" space-before.maximum="3pt">
                All rights reserved. No part of this publication may be reproduced, stored in a retrieval system or transmitted, in any form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the prior written permission of SAE.
              </fo:block>
            </fo:block>
          </fo:table-cell>
        </fo:table-row>
        <fo:table-row border-top-width="3pt" font-weight="bold">
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            text-align="left" number-rows-spanned="4">
            <fo:block>TO PLACE A DOCUMENT ORDER:</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            text-align="left">
            <fo:block>Tel:</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            text-align="left">
            <fo:block>877-606-7323 (inside USA and Canada)</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            text-align="left" number-rows-spanned="4">
            <fo:block font-size="9pt">
              SAE values your input. To provide feedback on this Technical Report, please visit <fo:basic-link external-destination="url('http://www.sae.org/technical/standards/PRODCODE')" text-decoration="underline" color="blue">http://www.sae.org/technical/standards/PRODCODE</fo:basic-link>
            </fo:block>
          </fo:table-cell>
        </fo:table-row>
        <fo:table-row font-weight="bold">
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            text-align="left">
            <fo:block>Tel:</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            text-align="left">
            <fo:block>+1 724-776-4970 (outside USA)</fo:block>
          </fo:table-cell>
        </fo:table-row>
        <fo:table-row font-weight="bold">
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            text-align="left">
            <fo:block>Fax:</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            text-align="left">
            <fo:block>724-776-0790</fo:block>
          </fo:table-cell>
        </fo:table-row>
        <fo:table-row font-weight="bold">
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            text-align="left">
            <fo:block>Email:</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            text-align="left">
            <fo:block>CustomerService@sae.org</fo:block>
          </fo:table-cell>
        </fo:table-row>
        <fo:table-row border-top-width="3pt" font-weight="bold">
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            text-align="left">
            <fo:block color="red">SAE WEB ADDRESS:</fo:block>
          </fo:table-cell>
          <fo:table-cell padding-left="0pt" padding-right="0pt"
            padding-top="0pt" padding-bottom="0pt"
            number-columns-spanned="3"
            text-align="left">
            <fo:block color="red">http://www.sae.org</fo:block>
          </fo:table-cell>
        </fo:table-row>
      </fo:table-body>
    </fo:table>
  </xsl:template>
</xsl:stylesheet>
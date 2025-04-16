<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="1.0">

    <xsl:output method="xml" encoding="UTF-8"/>

    <xsl:template match="/">

        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simple" page-height="11in" page-width="8.5in">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="simple">
                <fo:flow flow-name="xsl-region-body">

                    <!-- Title Section -->
                    <fo:block font-family="Helvetica" font-size="22pt" font-weight="bold" text-align="center" color="#4CAF50" margin-bottom="20pt">
                        Items List
                    </fo:block>

                    <!-- Loop for each item -->
                    <xsl:for-each select="/*[local-name()='getAllItemsResponse']/*[local-name()='getItemDto']">
                        <fo:block font-family="Arial, sans-serif" font-size="12pt" margin-top="10pt" line-height="1.6" border-bottom="1px solid #D3D3D3" padding-bottom="10pt" keep-together="always">

                            <!-- ID Section -->
                            <fo:block font-weight="bold" color="#2C3E50">
                                ID: <xsl:value-of select="*[local-name()='id']"/>
                            </fo:block>

                            <!-- Name Section -->
                            <fo:block font-weight="bold" color="#2C3E50">
                                Name: <xsl:value-of select="*[local-name()='name']"/>
                            </fo:block>

                            <!-- Price Section -->
                            <fo:block font-weight="bold" color="#2C3E50">
                                Price: $<xsl:value-of select="*[local-name()='price']"/>
                            </fo:block>

                            <!-- Description Section -->
                            <fo:block font-style="italic" color="#7F8C8D">
                                Description: <xsl:value-of select="*[local-name()='description']"/>
                            </fo:block>

                            <!-- Space after each item -->
                            <fo:block space-after="15pt"/>
                        </fo:block>
                    </xsl:for-each>

                </fo:flow>
            </fo:page-sequence>
        </fo:root>

    </xsl:template>

</xsl:stylesheet>

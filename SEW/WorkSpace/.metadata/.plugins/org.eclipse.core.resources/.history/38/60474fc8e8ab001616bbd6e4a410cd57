<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml" lang ="es" xml:lang="es">
            <head>
                <meta charset="UTF-8"/>
                <title>Ejemplo</title>
            </head>
            <body>
                <h1>Lista de alumnos</h1>
                <ul>
                    <xsl:for-each select="//alumno">
                        <xsl:sort select="datos/nombre/apellidos"/>
                        <xsl:sort select="datos/nombre/nombre_pila"/>
                        <xsl:apply-templates select="."/>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
	</xsl:template>
    <xsl:template match="alumno">
        <li>
            <xsl:value-of select="datos/nombre/nombre_pila"/>
            <xsl:text></xsl:text>
            <xsl:value-of select="datos/nombre/apellidos"/>
        </li>
    </xsl:template>
</xsl:stylesheet>
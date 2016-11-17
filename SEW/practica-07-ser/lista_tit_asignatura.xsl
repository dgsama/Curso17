<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="UTF-8" omit-xml-declaration="yes"
    indent="yes"/>
	<xsl:template match="/">
      <xsl:variable name="titulacion" select="'Grado en Ingeniería en Informática del Software'"/>
        <xsl:variable name="asignatura" select="'Fundamentos Informática'"/>
		<html xmlns="http://www.w3.org/1999/xhtml" lang ="es" xml:lang="es">
            <head>
                <meta charset="UTF-8"/>
                <title>Ejemplo</title>
            </head>
            <body>
                <ul>
                    <xsl:for-each select="//asignatura">
                        <xsl:sort select="ancestor::alumno/datos/nombre/apellidos"/>
                        <xsl:sort select="ancestor::alumno/datos/nombre/nombre_pila"/>
                        <xsl:if test="normalize-space(../n_titulacion)=$titulacion
                                        and normalize-space(n_asignatura)=$asignatura">
                            <xsl:apply-templates select="../.."/>
                        </xsl:if>
                    </xsl:for-each>
                </ul>                
            </body>
       </html>
	</xsl:template>
    <xsl:template match="alumno">
        <li><xsl:variable name="email" select="datos/email"/>
            <a href="mailto:{$email}">
            <xsl:value-of select="datos/nombre/nombre_pila"/>
            <xsl:text></xsl:text>
            <xsl:value-of select="datos/nombre/apellidos"/></a>
            <em><xsl:text>. Nº de matricula: </xsl:text>
            <xsl:value-of select="@matricula"/></em>
        </li>
    </xsl:template>
</xsl:stylesheet>
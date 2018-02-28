<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Created>2006-09-16T00:00:00Z</Created>
  <LastSaved>2018-02-28T11:13:52Z</LastSaved>
  <Version>15.00</Version>
 </DocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <AllowPNG/>
  <RemovePersonalInformation/>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>8010</WindowHeight>
  <WindowWidth>14805</WindowWidth>
  <WindowTopX>240</WindowTopX>
  <WindowTopY>105</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Bottom"/>
   <Borders/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="s65" ss:Name="超链接">
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#0000FF"
    ss:Underline="Single"/>
  </Style>
  <Style ss:ID="s16">
   <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
  </Style>
  <Style ss:ID="s67" ss:Parent="s65">
   <Alignment ss:Horizontal="Left" ss:Vertical="Bottom"/>
  </Style>
  <Style ss:ID="s68">
   <Alignment ss:Horizontal="Left" ss:Vertical="Bottom"/>
  </Style>
  <Style ss:ID="s69">
   <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="14" ss:Color="#000000"/>
  </Style>
 </Styles>
 <Worksheet ss:Name="Sheet1">
  <Table ss:ExpandedColumnCount="8" ss:ExpandedRowCount="10000" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="13.5">
   <Column ss:AutoFitWidth="0" ss:Width="63.75"/>
   <Column ss:AutoFitWidth="0" ss:Width="138"/>
   <Column ss:AutoFitWidth="0" ss:Width="66"/>
   <Column ss:AutoFitWidth="0" ss:Width="101.25"/>
   <Column ss:AutoFitWidth="0" ss:Width="67.5"/>
   <Column ss:Index="7" ss:AutoFitWidth="0" ss:Width="95.25"/>
   <Row ss:Height="18.75">
    <Cell ss:MergeAcross="7" ss:StyleID="s69"><Data ss:Type="String">任务详情报表</Data></Cell>
   </Row>
   <#list taskList as task>
       <Row>
           <Cell ss:StyleID="s16"><Data ss:Type="String">任务名称</Data></Cell>
           <Cell ss:StyleID="s16"><Data ss:Type="String">${task.taskName}</Data></Cell>
           <Cell ss:StyleID="s16"><Data ss:Type="String">任务编号</Data></Cell>
           <Cell ss:StyleID="s16"><Data ss:Type="String">${task.taskNumber}</Data></Cell>
           <Cell ss:StyleID="s16"><Data ss:Type="String">提交</Data></Cell>
           <Cell ss:StyleID="s16"><Data ss:Type="String">${task.userAccount}</Data></Cell>
           <Cell ss:StyleID="s16"><Data ss:Type="String">得分</Data></Cell>
           <Cell ss:StyleID="s16"><Data ss:Type="String">${task.taskMark}</Data></Cell>
       </Row>
       <Row>
           <Cell ss:StyleID="s16"><Data ss:Type="String">链接</Data></Cell>
           <Cell ss:MergeAcross="6" ss:StyleID="s67" ss:HRef="${task.taskLink}"><Data
                   ss:Type="String">${task.taskLink}</Data></Cell>
       </Row>
       <Row>
           <Cell ss:StyleID="s16"><Data ss:Type="String">标题</Data></Cell>
           <Cell ss:MergeAcross="6" ss:StyleID="s68"><Data ss:Type="String">${task.taskTitle}</Data></Cell>
       </Row>
       <Row>
           <Cell ss:StyleID="s16"><Data ss:Type="String">内容</Data></Cell>
           <Cell ss:MergeAcross="6" ss:StyleID="s68"><Data ss:Type="String">${task.taskContent}</Data></Cell>
       </Row>
       <Row>
           <Cell ss:StyleID="s16"><Data ss:Type="String">备注</Data></Cell>
           <Cell ss:MergeAcross="6" ss:StyleID="s68"><Data ss:Type="String">${task.taskExplain}</Data></Cell>
       </Row>
       <Row>
           <Cell ss:StyleID="s16"><Data ss:Type="String">截图地址</Data></Cell>
           <Cell ss:MergeAcross="6" ss:StyleID="s67" ss:HRef="http://121.199.4.149:8099/${task.taskImageUrl}"><Data
                   ss:Type="String">http://121.199.4.149:8099/${task.taskImageUrl}</Data></Cell>
       </Row>
       <Row>
           <Cell ss:StyleID="s16"><Data ss:Type="String">附件地址</Data></Cell>
           <Cell ss:MergeAcross="6" ss:StyleID="s67" ss:HRef="http://121.199.4.149:8099/${task.taskFileUrl}"><Data
                   ss:Type="String">http://121.199.4.149:8099/${task.taskFileUrl}</Data></Cell>
       </Row>
   </#list>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <Print>
    <ValidPrinterInfo/>
    <PaperSizeIndex>9</PaperSizeIndex>
    <HorizontalResolution>200</HorizontalResolution>
    <VerticalResolution>200</VerticalResolution>
    <NumberofCopies>0</NumberofCopies>
   </Print>
   <Selected/>
   <Panes>
    <Pane>
     <Number>3</Number>
     <ActiveRow>200</ActiveRow>
     <ActiveCol>200</ActiveCol>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>

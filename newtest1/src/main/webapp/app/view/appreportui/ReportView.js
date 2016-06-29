Ext.define('Newtest1.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Newtest1.view.appreportui.ReportViewController',
	            'Newtest1.view.appreportui.datagrid.DataGridPanel',
	            'Newtest1.view.appreportui.datagrid.DataGridView',
	            'Newtest1.view.appreportui.querycriteria.QueryCriteriaView',
	            'Newtest1.view.appreportui.chart.ChartView',
	            'Newtest1.view.appreportui.datapoint.DataPointView',
	            'Newtest1.view.googlemaps.map.MapPanel',
	            'Newtest1.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});

Ext.define('Newtest1.view.databrowsercalendar.DBCalendar', {
	extend : 'Newtest1.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Newtest1.view.databrowsercalendar.DBCalendarController',
	             'Newtest1.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});

import { Component, OnInit } from '@angular/core';
import { ReportService } from '../../shared/services/report/report.service';
import { FilterService } from '../../shared/services/filter/filter.service';

@Component({
  selector: 'staff-mack-report-page',
  templateUrl: './report-page.component.html',
  styleUrl: './report-page.component.scss'
})
export class ReportPageComponent implements OnInit {
  filters!: any[];
  pageSize = 10;
  page = 1;
  reports: any[] = [];

  constructor(
    private reportReportService: ReportService,
    private filterService: FilterService
  ) { }

  ngOnInit(): void {
    this.reportReportService.getReport().subscribe((groupedData: any) => {
      this.reports =  [groupedData];
      this.filters = this.filterService.getFilters(this.reports, 'reports');
    });
  }

  get totalPages(): number {
    console.log(this.reports, this.pageSize);
    if (this.reports.length > 0) {
      console.log('reports',this.reports);
      
    }
    
    return Math.ceil(this.reports.length / this.pageSize);
  }

  get pageNumbers(): number[] {
    return Array(this.totalPages).fill(0).map((x, i) => i + 1);
  }
}

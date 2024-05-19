import { Component } from "@angular/core";
import { LoaderService } from "./loader.service";
import { Subject } from "rxjs";

@Component({
    selector: "loader",
    template: `<ngx-loading [show]="(loading | async) || false"></ngx-loading>`,
})
export class LoaderComponent {
    loading: Subject<boolean> | null = this.loader.isLoading;
    constructor(
        private loader: LoaderService
    ) {}
}
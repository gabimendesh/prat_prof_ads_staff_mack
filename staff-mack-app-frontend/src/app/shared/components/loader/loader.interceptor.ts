import { HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { LoaderService } from "./loader.service";
import { finalize } from "rxjs";

@Injectable()
export class LoaderInterceptor implements HttpInterceptor {
    constructor(private loaderService: LoaderService) { }
    intercept(req: HttpRequest<any>, next: HttpHandler) {
        this.loaderService.show();
        return next.handle(req).pipe(finalize(() => this.loaderService.hide()));
    }
}
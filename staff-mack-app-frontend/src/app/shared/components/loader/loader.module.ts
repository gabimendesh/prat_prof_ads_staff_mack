import { NgModule } from "@angular/core";
import { LoaderComponent } from "./loader.component";
import { CommonModule } from "@angular/common";
import { NgxLoadingModule } from "ngx-loading";

@NgModule({
    declarations: [LoaderComponent],
    imports: [
        CommonModule,
        NgxLoadingModule.forRoot({
            fullScreenBackdrop: true
        })
    ],
    exports: [LoaderComponent],
})
export class LoaderModule {}
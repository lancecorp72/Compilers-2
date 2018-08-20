; ModuleID = 'x.c'
source_filename = "x.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-unknown-linux-gnu"

%struct.people = type { [30 x i8], i32 }

@.str = private unnamed_addr constant [14 x i8] c"Enter Name : \00", align 1
@.str.1 = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.2 = private unnamed_addr constant [13 x i8] c"Enter Age : \00", align 1
@.str.3 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.4 = private unnamed_addr constant [24 x i8] c"No of teenagers are %d\0A\00", align 1

; Function Attrs: nounwind uwtable
define dso_local i32 @main() local_unnamed_addr #0 {
entry:
  %p = alloca [3 x %struct.people], align 16
  %0 = getelementptr inbounds [3 x %struct.people], [3 x %struct.people]* %p, i64 0, i64 0, i32 0, i64 0
  call void @llvm.lifetime.start.p0i8(i64 108, i8* nonnull %0) #3
  %call = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([14 x i8], [14 x i8]* @.str, i64 0, i64 0))
  %call1 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i64 0, i64 0), i8* nonnull %0)
  %call2 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.2, i64 0, i64 0))
  %age = getelementptr inbounds [3 x %struct.people], [3 x %struct.people]* %p, i64 0, i64 0, i32 1
  %call5 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.3, i64 0, i64 0), i32* nonnull %age)
  %call.1 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([14 x i8], [14 x i8]* @.str, i64 0, i64 0))
  %arraydecay.1 = getelementptr inbounds [3 x %struct.people], [3 x %struct.people]* %p, i64 0, i64 1, i32 0, i64 0
  %call1.1 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i64 0, i64 0), i8* nonnull %arraydecay.1)
  %call2.1 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.2, i64 0, i64 0))
  %age.1 = getelementptr inbounds [3 x %struct.people], [3 x %struct.people]* %p, i64 0, i64 1, i32 1
  %call5.1 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.3, i64 0, i64 0), i32* nonnull %age.1)
  %call.2 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([14 x i8], [14 x i8]* @.str, i64 0, i64 0))
  %arraydecay.2 = getelementptr inbounds [3 x %struct.people], [3 x %struct.people]* %p, i64 0, i64 2, i32 0, i64 0
  %call1.2 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i64 0, i64 0), i8* nonnull %arraydecay.2)
  %call2.2 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str.2, i64 0, i64 0))
  %age.2 = getelementptr inbounds [3 x %struct.people], [3 x %struct.people]* %p, i64 0, i64 2, i32 1
  %call5.2 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.3, i64 0, i64 0), i32* nonnull %age.2)
  %1 = load i32, i32* %age, align 16, !tbaa !2
  %cmp14 = icmp slt i32 %1, 20
  %inc15 = zext i1 %cmp14 to i32
  %2 = load i32, i32* %age.1, align 4, !tbaa !2
  %cmp14.1 = icmp slt i32 %2, 20
  %inc15.1 = zext i1 %cmp14.1 to i32
  %spec.select.1 = add nuw nsw i32 %inc15, %inc15.1
  %3 = load i32, i32* %age.2, align 8, !tbaa !2
  %cmp14.2 = icmp slt i32 %3, 20
  %inc15.2 = zext i1 %cmp14.2 to i32
  %spec.select.2 = add nuw nsw i32 %spec.select.1, %inc15.2
  %call19 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([24 x i8], [24 x i8]* @.str.4, i64 0, i64 0), i32 %spec.select.2)
  call void @llvm.lifetime.end.p0i8(i64 108, i8* nonnull %0) #3
  ret i32 0
}

; Function Attrs: argmemonly nounwind
declare void @llvm.lifetime.start.p0i8(i64, i8* nocapture) #1

; Function Attrs: nounwind
declare dso_local i32 @printf(i8* nocapture readonly, ...) local_unnamed_addr #2

; Function Attrs: nounwind
declare dso_local i32 @__isoc99_scanf(i8* nocapture readonly, ...) local_unnamed_addr #2

; Function Attrs: argmemonly nounwind
declare void @llvm.lifetime.end.p0i8(i64, i8* nocapture) #1

attributes #0 = { nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { argmemonly nounwind }
attributes #2 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 8.0.0 (https://git.llvm.org/git/clang.git/ e6554b83e1b0d110f74b77594770e483dcc62e4f) (https://git.llvm.org/git/llvm.git/ a83fad8a70b06efade9e986bd720cde25d1c0905)"}
!2 = !{!3, !6, i64 32}
!3 = !{!"people", !4, i64 0, !6, i64 32}
!4 = !{!"omnipotent char", !5, i64 0}
!5 = !{!"Simple C/C++ TBAA"}
!6 = !{!"int", !4, i64 0}

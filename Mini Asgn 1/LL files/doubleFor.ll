; ModuleID = 'x.c'
source_filename = "x.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-unknown-linux-gnu"

@.str = private unnamed_addr constant [4 x i8] c"%d \00", align 1

; Function Attrs: nounwind uwtable
define dso_local i32 @main() local_unnamed_addr #0 {
entry:
  br label %for.cond1.preheader

for.cond1.preheader:                              ; preds = %if.end7, %entry
  %i.023 = phi i32 [ 2, %entry ], [ %inc9, %if.end7 ]
  %cmp221 = icmp ugt i32 %i.023, 2
  br i1 %cmp221, label %for.body4, label %if.then6.critedge

for.cond.cleanup:                                 ; preds = %if.end7
  %putchar = tail call i32 @putchar(i32 10)
  ret i32 0

for.cond1:                                        ; preds = %for.body4
  %cmp2 = icmp ult i32 %inc, %i.023
  br i1 %cmp2, label %for.body4, label %if.then6.critedge

for.body4:                                        ; preds = %for.cond1.preheader, %for.cond1
  %j.022 = phi i32 [ %inc, %for.cond1 ], [ 2, %for.cond1.preheader ]
  %rem = urem i32 %i.023, %j.022
  %cmp5 = icmp eq i32 %rem, 0
  %inc = add nuw nsw i32 %j.022, 1
  br i1 %cmp5, label %if.end7, label %for.cond1

if.then6.critedge:                                ; preds = %for.cond1, %for.cond1.preheader
  %call = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i64 0, i64 0), i32 %i.023)
  br label %if.end7

if.end7:                                          ; preds = %for.body4, %if.then6.critedge
  %inc9 = add nuw nsw i32 %i.023, 1
  %exitcond = icmp eq i32 %inc9, 100
  br i1 %exitcond, label %for.cond.cleanup, label %for.cond1.preheader
}

; Function Attrs: nounwind
declare dso_local i32 @printf(i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: nounwind
declare i32 @putchar(i32) local_unnamed_addr #2

attributes #0 = { nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { nounwind }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 8.0.0 (https://git.llvm.org/git/clang.git/ e6554b83e1b0d110f74b77594770e483dcc62e4f) (https://git.llvm.org/git/llvm.git/ a83fad8a70b06efade9e986bd720cde25d1c0905)"}

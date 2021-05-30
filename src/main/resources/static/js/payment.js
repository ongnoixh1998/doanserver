jQuery(function ($) {
    const submitPayment = (e)=>{
        e.preventDefault();
        const description = $("input[name=description]").val();
        const email = $("input[name=email]").val();
        const phoneNumber = $("input[name=phoneNumber]").val();
        const token = $("input[name=token]").val();
        let bpm = null;
        $(".bpm-item").each((index,target)=>{
            if ($(target).hasClass("border")){
                bpm = $(target).data("bankid");
            }
        })
        const paramRequest = new URLSearchParams({
            token:token,
            description:description,
            email:email,
            phoneNumber:phoneNumber,
            bpm_id:bpm
        })
        if (bpm){
            window.location.href = "/payment/submit?"+paramRequest.toString();
        }
    }
    $(document.body).on("click",".bpm-item",(e)=>{
        let id = $(e.target).data("bankid");
        if (!id){
            id = $(e.target).parent().data("bankid");
        }
        $(".bpm-item").each((index,target)=>{
            const element = $(target);
            const bankid = element.data("bankid");
            if (bankid===id){
                if (!element.hasClass("border")){
                    element.addClass("border")
                }else {
                    element.removeClass("border")
                }
            }else {
                element.removeClass("border")
            }
        });

    });
    $(document.body).on('click','.btn-submit-payment',submitPayment)
})
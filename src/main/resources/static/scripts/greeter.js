
		$("#myModal").on("shown.bs.modal", function() {
			$("#myInput").trigger("focus");
		});
	
	
				
		$(".sizeSelectCheck").click(function(){
			var item = $(this).siblings('label').find(".item").text()
			var price = $(this).siblings('label').find(".price").text()
			var id = item.replace(/\s+/g, '')
			var summaryRowWash = "<tr id="+id+" class='currentItems wash'><td class='text-left summaryItem' style='border: none;'>"+item+"</td><td class='summaryPrice' style='float: right; border: none;'>"+price+"</tr>"
			var summaryRowExtra = "<tr id="+id+" class='currentItems extra'><td class='text-left summaryItem' style='border: none;'>"+item+"</td><td class='summaryPrice' style='float: right; border: none;'>"+price+"</tr>"
			
			var sum = 0.00
			if($(this).is(':checked')&&$(this).hasClass("wash")){
				$(".currentItems.wash").remove()
				console.log("checked wash")
				$("#summary").prepend(summaryRowWash)
			}else if($(this).is(':checked')&&$(this).hasClass("extra")){
				console.log("checked extra")
				$("#summary tr").last().before(summaryRowExtra)
			}else{
				console.log(id)
				$("#" + id).remove()
			}
			
		 	$(".summaryPrice").each(function(){
		 	    
		 	    sum = sum + parseFloat($(this).text()) 
		 	    console.log(sum)
		 	    })
		 	    
		 	$(".sumPrice").text(sum)
		 	
		 	
          }) 

    
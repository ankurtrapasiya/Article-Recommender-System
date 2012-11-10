(function(){
    $.getJSON("ArticleController?freq=true", function(dt){
        
        var temp=Handlebars.compile($("#article-template").html());                        
        $("#article-contents").html(temp(dt.content));
                                                                                            
    });                                
                
    $.getJSON("ArticleController?action=getall", function(dt){
                   
        var temp=Handlebars.compile($("#interest-template").html());                        
        $(".sidebar .sidebar-links").html(temp(dt.content));
                   
        $(".sidebar-links").find("a").on("click",function(e){
            e.preventDefault();
            var linktype = $(this).attr("rel");    
                        
            var url="ArticleController?interestid=";
            var url1=url.concat(linktype);
                        
            $.get(url1, function(dt){

                var temp=Handlebars.compile($("#article-template").html());                        
                $("#article-contents").html(temp(dt.content));
                            
                console.log(dt.content);
                            
                console.log($(".meta-data").find("input"));
                            
                            
                $(".meta-data").find("input").on("click",function(e){
            
                    var x=$(this);
                    var id=$(this).attr("id");
                    var val=$(this).attr("data");
                                
                    console.log(id);
                    console.log(val);
                                
                    if(id==="addtofav")
                    {
                        var url="ArticleController?action=addtofavourites&articleid=";
                        var url1=url.concat(val);
                                    
                        $.post(url1,function(dt){
                                                                                
                            x.attr('disabled','disabled');
                            x.val('in favourites');
                                        
                        },"json");
                                    
                    }
                    else if(id==="readlater")
                    {
                        var url="ArticleController?action=readitlater&articleid=";
                        var url1=url.concat(val);
                                    
                        $.post(url1,function(dt){
                                        
                            x.attr('disabled','disabled');
                            x.val('in readlater');
                                        
                        },"json");
                    }
                                
                });
                            
                            
                $(".like-dislike").find("a").on("click",function(e){
                            
                    e.preventDefault();
                    var val=$(this).attr("rel");  
                                
                    var id=$(this).attr("id");
                                
                    var upvote=$(this).parent().find(".counter-up");   
                    var downvote=$(this).parent().find(".counter-down");
                                                                                                                               
                    if(id==="down")
                    {
                        var url="ArticleController?action=downvote&articleid=";
                        var url1=url.concat(val);
                                
                        $.post(url1,function(dt)
                        {
                            downvote.html("<label>" + dt.content[0].downvote + "</label>");   
                            upvote.html("<label>" + dt.content[1].upvote + "</label>");
                        },"json");
                    }
                    else
                    {
                                        
                        var url="ArticleController?action=upvote&articleid=";
                        var url1=url.concat(val);
                                
                        $.post(url1,function(dt)
                        {
                                     
                            console.log(dt.content[0].upvote);
                            console.log(dt.content[1].downvote);
                                        
                            console.log(upvote);
                            console.log(downvote);
                                        
                            upvote.html("<label>" + dt.content[0].upvote + "</label>");                                        
                            downvote.html("<label>" + dt.content[1].downvote + "</label>");   
                                    
                        },"json");
                    }                                
                });
                            
            },"json");
                        
        });                           
    });  
                
    $.post("GetAllFriends?action=givemefriends", function(dt){
        $("#demo-input-local").tokenInput(dt.content,{
            theme:"facebook"
        });
    }, "json");
                
                
    $(".modalbox").fancybox();
                
                
    //  $("#suggestionform").submit(function() { return false; });
                                
    $("#send").on("click", function(){
                    
        var msgval  = $("#demo-input-local").val();
        var msglen    = msgval.length;			
        var articleId= $("#hdnArticleId").val();
                                                                               
        if(msglen <= 0) {
            $("#demo-input-local").addClass("error");
        }
        else
        {
    
            $("#demo-input-local").removeClass("error");       
            ("#send").replaceWith("<em>suggestions sent</em>");
                      
            $.ajax({
                type: 'POST',
                url: 'ArticleController',
                data: {
                    'action':'suggest',
                    'friendid':msgval,
                    'articleid':articleId
                },
                success: function(data) {
                    if(data.content[0].data === "true") {
                        $(this).html("<label>suggestion sent</label>");
                    }
                }
            });
 
        }                   
    });

})();
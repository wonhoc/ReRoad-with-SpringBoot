$(document).ready(function (){


    ( /* att_zone : 이미지들이 들어갈 위치 id, btn : file tag id */
        imageView = function imageView(att_zone, btn){

            var attZone = document.getElementById(att_zone);
            var btnAtt = document.getElementById(btn)
            var sel_files = [];


            var div_style = 'display:inline-block;position:relative;'
                + 'width:150px;height:120px;margin:5px;border:1px solid teal;z-index:1';

            var img_style = 'width:100%;height:100%;z-index:none';

            var chk_style = 'width:30px;height:30px;position:absolute;font-size:24px;'
                + 'right:0px;bottom:0px;z-index:999;background-color:rgba(255,255,255,0.1);color:teal';

            btnAtt.onchange = function(e){
                var files = e.target.files;
                var fileArr = Array.prototype.slice.call(files)
                for(f of fileArr){
                    imageLoader(f);
                }
            }



            attZone.addEventListener('dragenter', function(e){
                e.preventDefault();
                e.stopPropagation();
            }, false)

            attZone.addEventListener('dragover', function(e){
                e.preventDefault();
                e.stopPropagation();

            }, false)

            attZone.addEventListener('drop', function(e){

                var files = {};
                e.preventDefault();
                e.stopPropagation();
                var dt = e.dataTransfer;
                files = dt.files;
                for(f of files){
                    let name = f.name.split('.').pop().toLowerCase(); //확장자
                    //배열에 추출한 확장자가 존재하는지 체크
                    if($.inArray(name, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                        Swal.fire({ icon: 'error', title: '경고', text: '이미지파일이 아닙니다.' ,});
                        return;
                    }else {
                        imageLoader(f);
                    }

                    }

            }, false)




            imageLoader = function(file){
                sel_files.push(file);
                var reader = new FileReader();
                reader.onload = function(ee){
                    let img = document.createElement('img')
                    img.setAttribute('style', img_style)
                    img.src = ee.target.result;
                    attZone.appendChild(makeDiv(img, file));
                }

                reader.readAsDataURL(file);
            }


            makeDiv = function(img, file){
                var div = document.createElement('div')
                div.setAttribute('style', div_style)

                var btn = document.createElement('input')
                btn.setAttribute('type', 'button')
                btn.setAttribute('value', 'x')
                btn.setAttribute('delFile', file.name);
                btn.setAttribute('style', chk_style);
                btn.onclick = function(ev){
                    var ele = ev.srcElement;
                    var delFile = ele.getAttribute('delFile');
                    for(var i=0 ;i<sel_files.length; i++){
                        if(delFile== sel_files[i].name){
                            sel_files.splice(i, 1);
                        }
                    }

                    dt = new DataTransfer();
                    for(f in sel_files) {
                        var file = sel_files[f];
                        dt.items.add(file);
                    }
                    btnAtt.files = dt.files;
                    var p = ele.parentNode;
                    attZone.removeChild(p)
                }
                div.appendChild(img)
                div.appendChild(btn)
                return div
            }
        }
    )('att_zone', 'fileList')


    $(document).on('click', '#saveBtn', function (){
        if(($('#boardTitle').val() == '')){
            Swal.fire({ icon: 'warning', title: '경고', text: '제목을 입력해주세요' ,});
            return false;
        }
        if ($('#boardContent').val() == ''){
            Swal.fire({ icon: 'warning', title: '경고', text: '내용을 입력해주세요' ,});
            return false;
        }
    })

})
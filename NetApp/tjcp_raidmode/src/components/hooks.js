import {useEffect, useRef} from 'react'

export function useOnDraw(onDraw){


   const canRef = useRef(null);

   const isDrawingRef = useRef(false);

   const mouseMoveListenerRef = useRef(null);
   const mouseUpListenerRef = useRef(null);
   const mouseDownListenerRef = useRef(null);

   const prevPointRef = useRef(null);




   function setCanvasRef(ref){

    if(!ref) return;
    if(canRef.current){

    canRef.current.removeEventListener("mousedown", mouseDownListenerRef.current);

    }

    canRef.current = ref
    initMouseMoveListener();
    initMouseDownListener();
    initMouseuUpListener();

   }

   function initMouseMoveListener(){
    const mouseMoveListener = (e)  =>{
         
        if(isDrawingRef.current){

            const point = Compointcanvas(e.clientX, e.clientY);
            const ctx = canRef.current.getContext('2d');
            if(onDraw) onDraw(ctx,point,prevPointRef.current);
            prevPointRef.current = point;
            
            console.log(point)
    
        }
        
        

    }
    mouseMoveListenerRef.current = mouseMoveListener;
    window.addEventListener("mousemove", mouseMoveListener);
   }

  
   function initMouseDownListener(){
    if (!canRef.current) return;
    const mdListener = () => {

        isDrawingRef.current = true;

    }
    mouseDownListenerRef.current = mdListener;
    canRef.current.addEventListener("mousedown", mdListener);

   }


   function initMouseuUpListener(){
    if (!canRef.current) return;
    const muListener = () => {

        isDrawingRef.current = false;
        prevPointRef.current = null;

    }
    mouseUpListenerRef.current = muListener;
    window.addEventListener("mouseup", muListener);

   }


   function Compointcanvas(clientX,clientY){
        if(canRef.current){
            const boundingRec = canRef.current.getBoundingClientRect();
            return{
                x :  clientX - boundingRec.left  ,
                y : clientY - boundingRec.top
            }
        }else{
            return null;
        }
   }



   return setCanvasRef;


};
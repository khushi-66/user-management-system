export function ProfilePhoto({profileUrl,username,size}){
    const getInitials=(name)=>{
         if(!name)return"";

         return  name.trim()  //remove starting and ending whitespace
        .split(/\s+/)   //remove middle space ->["khushi","sahu"]
        .map(word => word[0])   //iterate array and receive first letter's array ->["k","s"]
        .join("")    //convert array to string  ->ks
        .toUpperCase();   //convert to uppercase
 }
 console.log(profileUrl)
    return <>
   
         <div
            className="border border-1 border-secondary-subtle rounded-circle bg-secondary  bg-opacity-75 text-light d-flex align-items-center justify-content-center"
            style={{
                height: size||"clamp(55px, 8vw, 85px)",
                width: size||"clamp(55px, 8vw, 85px)",
                aspectRatio: "1 / 1",
                fontSize: `calc(${size} * 0.4)`
            }}
        >
            
              {profileUrl === 'null' ? (
             <img
                    src={`http://localhost:9090/${profileUrl}`}
                    alt={username}
                    className="w-100 h-100 rounded-circle"
                    style={{ objectFit: "cover" }}
                />
                
            ) : (
                getInitials(username)
               
            )}
             
           
           
        </div>
        
        </>
    
}
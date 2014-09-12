import ("bufio";"fmt";"os";"strings";"strconv")
 
func main() {
    f, _ := os.Open("./CSVData.csv")
    r := bufio.NewReader(f)
    totalVal := 0.0
 
    for true{
        line, err := r.ReadString('\n')
        if(err != nil){break}
 
        data := strings.Split(line,",")
        val, _ := strconv.ParseFloat(strings.Replace(strings.Replace(data[1],"+","",-1),"\"","",-1), 32)
 
        totalVal += val;
    }
    fmt.Println("Total: ", totalVal)
}
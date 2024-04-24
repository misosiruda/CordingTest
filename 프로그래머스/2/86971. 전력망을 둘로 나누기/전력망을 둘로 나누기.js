class Node
{
    constructor (number, data)
    {
        this.number = number;
        this.child = [];
        this.data = 1;
    }
}

class Tree
{
    constructor ()
    {
        this.root = null;
    }

    add(parent, number)
    {
        const node = this.root;
        if (node === null)
        {
            this.root = new Node(number);
            return true;
        }
        else 
        {
            let isExist = false;
            const searchTree = function (node)
            {
                if (node.number === parent)
                {
                    isExist = true;
                    if (!(node.child.reduce((a, b) => a || b.number === number, false)))
                    {
                        node.child.push(new Node(number));
                    }
                }
                else
                {
                    if (node.child[0] === null) return;
                    node.child.map(a => searchTree(a));
                }
            };
            searchTree(node);
            return isExist;
        }
    }

    setData(n)
    {
        const node = this.root;
        let gapArr = [];
        const sumTree = function (node)
        {
            if (node.child[0] === null)
            {
                gapArr.push(Math.abs(n - (node.data * 2)));
                return node.data;
            }
            else
            {
                let sum = 0;
                node.child.map(a => sum += sumTree(a));
                node.data += sum;
                gapArr.push(Math.abs(n - (node.data * 2)));
                return node.data;
            }
        };
        sumTree(node);
        return Math.min(...gapArr);
    }
}

function MakeTree(wires)
{
    let tree = new Tree();
    wires.sort((a, b) => a[0] - b[0]);
    tree.add(0, 1);
    while (0 < wires.length)
    {
        for (let i = 0; i < wires.length; i++)
        {
            let [a, b] = wires[i];
            if (!tree.add(a, b))
            {
                if (tree.add(b, a))
                {
                    wires.splice(i, 1);
                    i--;
                }
            }
            else
            {
                wires.splice(i, 1);
                i--;
            }
        }
    }
    return tree;
}

function solution(n, wires)
{
    let tree = MakeTree(wires);
    var answer = tree.setData(n);
    return answer;
}
let preArr = [];
let postArr = [];

class Node
{
  constructor (data, left = null, right = null)
  {
    this.data = data;
    this.left = left;
    this.right = right;
  }
}

class BST
{
  constructor ()
  {
    this.root = null;
  }

  add(data)
  {
    const node = this.root;
    if (node === null)
    {
      this.root = new Node(data);
      return;
    }
    else 
    {
      const searchTree = function (node)
      {
        if (data[0] < node.data[0]) 
        {
          if (node.left === null)
          {
            node.left = new Node(data);
            return;
          }
          else if (node.left !== null)
          {
            //left에 함수 있을 시 재귀 함수 적용
            return searchTree(node.left);
          }
        }
        else if (data[0] > node.data[0])
        {
          if (node.right === null)
          {
            node.right = new Node(data);
            return;
          }
          else if (node.right !== null)
          {
            return searchTree(node.right);
          }
        }
        else
        {
          return null;
        }
      };
      return searchTree(node);
    }
  }

  remove(data)
  {
    //제거할 data의 파라미터를 두번째에 놓았다.
    const removeNode = function (node, data) 
    {
      if (node == null)
      {
        return null;
      }
      if (data == node.data)
      {
        // node has no children ~ 밑에 뿌리가 없는 노드
        if (node.left == null && node.right == null)
        {
          return null;
        }
        // node has no left child  ~ left는 없는 경우 node right가 해당 삭제 데이터에 들어간다.
        if (node.left == null)
        {
          return node.right;
        }
        // node has no right child
        if (node.right == null)
        {
          return node.left;
        }
        // node has two children
        var tempNode = node.right;
        //tempNode는 삭제할 node의 right가 되고
        while (tempNode.left !== null)
        {
          tempNode = tempNode.left; //다시 node right의 left가 된다.
        }
        node.data = tempNode.data; //그리고 삭제 node에는 위의 tempnode가 들어가게된다.
        node.right = removeNode(node.right, tempNode.data);
        return node;
      }
      else if (data < node.data)
      {
        node.left = removeNode(node.left, data);
        return node;
      }
      else
      {
        node.right = removeNode(node.right, data);
        return node;
      }
    };
    this.root = removeNode(this.root, data);
  }
}

function recursivePreOrder(node)
{
  if (!node)
  {
    return;
  }
  preArr.push(node.data[2]);
  recursivePreOrder(node.left);
  recursivePreOrder(node.right);
}
function recursivePostOrder(node)
{
  if (!node)
  {
    return;
  }
  recursivePostOrder(node.left);
  recursivePostOrder(node.right);
  postArr.push(node.data[2]);
}

function solution(nodeinfo)
{
  for (let i = 1; i <= nodeinfo.length; i++)
  {
    nodeinfo[i - 1][2] = i;
  }
  nodeinfo.sort((a, b) => b[1] - a[1]);
  let tree = new BST();
  // console.log(nodeinfo);
  for (e of nodeinfo)
  {
    tree.add(e);
  }
  recursivePreOrder(tree.root);
  recursivePostOrder(tree.root);
  return [preArr, postArr];
}
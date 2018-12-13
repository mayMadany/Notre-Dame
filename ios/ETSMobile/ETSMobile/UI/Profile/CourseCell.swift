//
//  CourseCell.swift
//  ETSMobile
//
//  Created by Emmanuel Proulx on 2018-12-13.
//  Copyright © 2018 ApplETS. All rights reserved.
//

import UIKit

class CourseCell: UICollectionViewCell {
    
    @IBOutlet weak var courseTitle: UILabel!
    
    func displayContent(title:String) {
        courseTitle.text = title
        
    }
}
